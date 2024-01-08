package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.enumtype.EOrderStatus;
import nta.bookstore.api.common.exception.AppException;
import nta.bookstore.api.common.exception.NotFoundException;
import nta.bookstore.api.common.mapper.OrderDetailMapper;
import nta.bookstore.api.common.mapper.OrderMapper;
import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.entity.*;
import nta.bookstore.api.repository.*;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto.Detail getOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ResponseConst.ORDER_NOT_FOUND));
        OrderDto.Detail detailDto = orderMapper.toDetailDto(orderEntity);
        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findAllByOrderId(orderId);
        detailDto.setOrderDetailDtos(orderDetailMapper.toDtos(orderDetailEntities));
        detailDto.setTotalValue(computeOrderTotalValue(orderId));
        return detailDto;
    }

    @Override
    public List<OrderDto.Overview> getCurrentUserOrders(AuthUserDetails authUserDetails) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        List<OrderEntity> orders = orderRepository.findAllByUserId(userEntity.getId());
        return orders.stream()
                .map(order -> {
                    OrderDto.Overview overviewDto = orderMapper.toOverviewDto(order);
                    overviewDto.setTotalValue(computeOrderTotalValue(order.getId()));
                    return overviewDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto.Overview> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAllAndOrderByCreatedAt();
        return orders.stream()
                .map(order -> {
                    OrderDto.Overview overviewDto = orderMapper.toOverviewDto(order);
                    overviewDto.setTotalValue(computeOrderTotalValue(order.getId()));
                    return overviewDto;
                }).collect(Collectors.toList());
    }

    @Override
    public void checkCartItems(AuthUserDetails authUserDetails) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        List<CartItemEntity> cartItems = cartItemRepository.findAllByUserId(userEntity.getId());
        cartItems.forEach(cartItem -> {
            BookEntity book = cartItem.getBook();
            if (cartItem.getQuantity() > book.getRemainingQuantity()) {
                String errorMessage = String.format(ResponseConst.NO_MORE_BOOKS_TEMPLATE, book.getRemainingQuantity(), book.getTitle());
                throw new AppException(ResponseConst.NO_MORE_BOOKS_CODE, errorMessage);
            }
        });
    }

    @Override
    @Transactional
    public OrderDto.Detail createOrder(AuthUserDetails authUserDetails, OrderDto.Create createDto) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        OrderEntity orderEntity = OrderEntity
                .builder()
                .user(userEntity)
                .fullName(createDto.getFullName())
                .phone(createDto.getPhone())
                .address(createDto.getAddress())
                .orderStatus(EOrderStatus.PENDING)
                .build();
        orderRepository.save(orderEntity);

        List<CartItemEntity> cartItems = cartItemRepository.findAllByUserId(userEntity.getId());
        List<OrderDetailEntity> orderDetailEntities = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            BookEntity book = cartItem.getBook();
            OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder()
                    .order(orderEntity)
                    .book(book)
                    .quantity(cartItem.getQuantity())
                    .importingPrice(book.getImportingPrice())
                    .sellingPrice(book.getSellingPrice())
                    .build();
            orderDetailEntities.add(orderDetailEntity);

            book.setRemainingQuantity(book.getRemainingQuantity() - cartItem.getQuantity());
            book.setSoldQuantity(book.getSoldQuantity() + cartItem.getQuantity());
            bookRepository.save(book);
        });

        orderDetailRepository.saveAll(orderDetailEntities);

        // clear this user shopping cart
        cartItemRepository.deleteAll(cartItems);

        OrderDto.Detail detailDto = orderMapper.toDetailDto(orderEntity);
        detailDto.setOrderDetailDtos(orderDetailMapper.toDtos(orderDetailEntities));
        detailDto.setTotalValue(computeOrderTotalValue(orderEntity.getId()));
        return detailDto;
    }

    @Override
    public OrderDto.Detail updateOrder(Long orderId, OrderDto.Update updateDto) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ResponseConst.ORDER_NOT_FOUND));
        order.setOrderStatus(updateDto.getStatus());
        List<OrderDetailEntity> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        if (updateDto.getStatus().equals(EOrderStatus.REJECTED)) {
            orderDetails.forEach(orderDetail -> {
                var book = orderDetail.getBook();
                book.setSoldQuantity(book.getSoldQuantity() - orderDetail.getQuantity());
                book.setRemainingQuantity(book.getRemainingQuantity() + orderDetail.getQuantity());
                bookRepository.save(book);
            });
        }
        OrderDto.Detail detailDto = orderMapper.toDetailDto(order);
        detailDto.setOrderDetailDtos(orderDetailMapper.toDtos(orderDetails));
        detailDto.setTotalValue(computeOrderTotalValue(order.getId()));
        return detailDto;
    }

    public double computeOrderTotalValue(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(ResponseConst.ORDER_NOT_FOUND));
        List<OrderDetailEntity> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
        double totalOrderValue = 0;
        for (OrderDetailEntity odt : orderDetails) {
            totalOrderValue = totalOrderValue + odt.getQuantity() * odt.getSellingPrice();
        }
        return totalOrderValue;
    }
}

package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.repository.*;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.OrderService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public OrderDto.Detail getOrder(Long orderId) {
        return null;
    }

    @Override
    public OrderDto.Overview getCurrentUserOrders(AuthUserDetails authUserDetails) {
        return null;
    }

    @Override
    public OrderDto.Overview getAllOrders() {
        return null;
    }

    @Override
    public OrderDto.Detail createOrder(OrderDto.Create createDto) {
        return null;
    }

    @Override
    public OrderDto.Detail updateOrder(Long orderId, OrderDto.Update updateDto) {
        return null;
    }
}

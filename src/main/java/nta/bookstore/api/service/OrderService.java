package nta.bookstore.api.service;

import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.security.AuthUserDetails;

import java.util.List;

public interface OrderService {
    OrderDto.Detail getOrder(Long orderId);

    List<OrderDto.Overview> getCurrentUserOrders(AuthUserDetails authUserDetails);

    List<OrderDto.Overview> getAllOrders();

    void checkCartItems(AuthUserDetails authUserDetails);

    OrderDto.Detail createOrder(AuthUserDetails authUserDetails, OrderDto.Create createDto);

    OrderDto.Detail updateOrder(Long orderId, OrderDto.Update updateDto);
}

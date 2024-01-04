package nta.bookstore.api.service;

import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.security.AuthUserDetails;

public interface OrderService {
    OrderDto.Detail getOrder(Long orderId);

    OrderDto.Overview getCurrentUserOrders(AuthUserDetails authUserDetails);

    OrderDto.Overview getAllOrders();

    OrderDto.Detail createOrder(OrderDto.Create createDto);

    OrderDto.Detail updateOrder(Long orderId, OrderDto.Update updateDto);
}

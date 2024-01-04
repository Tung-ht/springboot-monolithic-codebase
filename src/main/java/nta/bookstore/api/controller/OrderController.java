package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public AppResponse<OrderDto.Detail> getOrder(@PathVariable("id") Long orderId) {
        return AppResponse.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/current-user")
    public AppResponse<OrderDto.Overview> getCurrentUserOrders(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return AppResponse.ok(orderService.getCurrentUserOrders(authUserDetails));
    }

    @GetMapping("/all-user")
    public AppResponse<OrderDto.Overview> getAllOrders() {
        return AppResponse.ok(orderService.getAllOrders());
    }

    @PostMapping
    public AppResponse<OrderDto.Detail> createOrder(OrderDto.Create createDto) {
        return AppResponse.ok(orderService.createOrder(createDto));
    }

    @PutMapping("/{id}")
    public AppResponse<OrderDto.Detail> updateOrder(@PathVariable("id") Long orderId,
                                             OrderDto.Update updateDto) {
        return AppResponse.ok(orderService.updateOrder(orderId, updateDto));
    }
}

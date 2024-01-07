package nta.bookstore.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public AppResponse<List<OrderDto.Overview>> getCurrentUserOrders(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return AppResponse.ok(orderService.getCurrentUserOrders(authUserDetails));
    }

    @GetMapping("/all-user")
    public AppResponse<List<OrderDto.Overview>> getAllOrders() {
        return AppResponse.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Call this api to check the cart before making order. \n" +
            "Throw exception when store does not have enough products")
    @GetMapping
    public AppResponse<?> checkCartItem(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        orderService.checkCartItems(authUserDetails);
        return AppResponse.ok();
    }

    @Operation(summary = "Make all cart items of this user into order, and delete all cart items")
    @PostMapping
    public AppResponse<OrderDto.Detail> createOrder(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            OrderDto.Create createDto) {
        return AppResponse.ok(orderService.createOrder(authUserDetails, createDto));
    }

    @PutMapping("/{id}")
    public AppResponse<OrderDto.Detail> updateOrder(@PathVariable("id") Long orderId,
                                                    OrderDto.Update updateDto) {
        return AppResponse.ok(orderService.updateOrder(orderId, updateDto));
    }
}

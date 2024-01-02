package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.CartItemDto;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shopping-cart-items")
public class ShoppingCartController {
    private final CartService cartService;

    @GetMapping
    public AppResponse<List<CartItemDto>> getCart(@AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return AppResponse.ok(cartService.getCart(authUserDetails));
    }

    @PostMapping
    public AppResponse<CartItemDto> addBookToCart(CartItemDto.Create createDto) {
        return AppResponse.ok(cartService.createCartItem(createDto));
    }

    @PutMapping
    public AppResponse<CartItemDto> updateCart(CartItemDto.Update updateDto) {
        return AppResponse.ok(cartService.updateCartItem(updateDto));
    }
}

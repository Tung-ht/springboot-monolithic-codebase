package nta.bookstore.api.service;

import nta.bookstore.api.dto.CartItemDto;
import nta.bookstore.api.security.AuthUserDetails;

import java.util.List;

public interface CartService {
    List<CartItemDto> getCart(AuthUserDetails authUserDetails);

    CartItemDto createCartItem(CartItemDto.Create createDto);

    CartItemDto updateCartItem(CartItemDto.Update updateDto);

    void deleteCartItem(CartItemDto.Delete deleteDto);

    void clearCartItems(AuthUserDetails authUserDetails);
}

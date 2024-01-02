package nta.bookstore.api.service.impl;


import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.exception.NotFoundException;
import nta.bookstore.api.dto.CartItemDto;
import nta.bookstore.api.entity.UserEntity;
import nta.bookstore.api.repository.UserRepository;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;

    @Override
    public List<CartItemDto> getCart(AuthUserDetails authUserDetails) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        return null;
    }

    @Override
    public CartItemDto createCartItem(CartItemDto.Create createDto) {
        return null;
    }

    @Override
    public CartItemDto updateCartItem(CartItemDto.Update update) {
        return null;
    }
}

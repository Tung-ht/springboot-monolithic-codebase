package nta.bookstore.api.service.impl;


import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.exception.NotFoundException;
import nta.bookstore.api.common.mapper.CartItemMapper;
import nta.bookstore.api.dto.CartItemDto;
import nta.bookstore.api.entity.BookEntity;
import nta.bookstore.api.entity.CartItemEntity;
import nta.bookstore.api.entity.UserEntity;
import nta.bookstore.api.repository.BookRepository;
import nta.bookstore.api.repository.CartItemRepository;
import nta.bookstore.api.repository.UserRepository;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final CartItemMapper cartItemMapper;
    private final ImageService imageService;

    @Override
    public List<CartItemDto> getCart(AuthUserDetails authUserDetails) {
        UserEntity userEntity = userRepository.findById(authUserDetails.getId()).orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));
        return cartItemRepository.findAllByUserId(userEntity.getId())
                .stream()
                .map(item -> {
                    var dto = cartItemMapper.toDto(item);
                    dto.setImageBase64Src(imageService.loadImageAsBase64Source(item.getBook().getImgUrl()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CartItemDto createCartItem(CartItemDto.Create createDto) {
        UserEntity user = userRepository.findById(createDto.getUserId())
                .orElseThrow(() -> new NotFoundException(ResponseConst.USER_NOT_FOUND));

        BookEntity book = bookRepository.findById(createDto.getBookId())
                .orElseThrow(() -> new NotFoundException(ResponseConst.BOOK_NOT_FOUND));

        CartItemEntity cartItem = cartItemRepository.findByUserIdAndBookId(createDto.getUserId(), createDto.getBookId());
        CartItemDto dto = new CartItemDto();
        if (cartItem == null) {
            CartItemEntity newItem = CartItemEntity
                    .builder()
                    .book(book)
                    .user(user)
                    .quantity(1L)
                    .build();
            dto = cartItemMapper.toDto(cartItemRepository.save(newItem));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            dto = cartItemMapper.toDto(cartItemRepository.save(cartItem));
        }
        dto.setImageBase64Src(imageService.loadImageAsBase64Source(book.getImgUrl()));
        return dto;
    }

    @Override
    public CartItemDto updateCartItem(CartItemDto.Update updateDto) {
        CartItemEntity cartItem = cartItemRepository.findById(updateDto.getCartItemId())
                .orElseThrow(() -> new NotFoundException(ResponseConst.CART_ITEM_NOT_FOUND));
        cartItem.setQuantity(updateDto.getQuantity());
        CartItemDto dto = cartItemMapper.toDto(cartItemRepository.save(cartItem));
        dto.setImageBase64Src(imageService.loadImageAsBase64Source(cartItem.getBook().getImgUrl()));
        return dto;
    }

    @Override
    public void deleteCartItem(CartItemDto.Delete deleteDto) {
        cartItemRepository.deleteById(deleteDto.getCartItemId());
    }
}

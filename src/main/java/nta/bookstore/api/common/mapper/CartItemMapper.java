package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.CartItemDto;
import nta.bookstore.api.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "title", source = "book.title")
    @Mapping(target = "author", source = "book.author")
    @Mapping(target = "description", source = "book.description")
    @Mapping(target = "category", source = "book.category")
    @Mapping(target = "sellingPrice", source = "book.sellingPrice")
    CartItemDto toDto(CartItemEntity entity);

    List<CartItemDto> toDtos(List<CartItemEntity> entities);

}

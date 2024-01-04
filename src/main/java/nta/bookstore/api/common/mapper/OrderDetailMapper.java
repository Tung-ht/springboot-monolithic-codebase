package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.OrderDetailDto;
import nta.bookstore.api.entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "title", source = "book.title")
    @Mapping(target = "author", source = "book.author")
    @Mapping(target = "description", source = "book.description")
    @Mapping(target = "category", source = "book.category")
    OrderDetailDto toDto(OrderDetailEntity entity);
}

package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.OrderDto;
import nta.bookstore.api.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "email", source = "user.email")
    OrderDto.Overview toOverviewDto(OrderEntity entity);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "email", source = "user.email")
    OrderDto.Detail toDetailDto(OrderEntity entity);
}

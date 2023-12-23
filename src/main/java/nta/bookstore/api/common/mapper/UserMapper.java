package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.UserDto;
import nta.bookstore.api.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
}

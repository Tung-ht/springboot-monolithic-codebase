package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.UserDto;
import nta.bookstore.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);

    UserEntity toEntity(UserDto userEntity);

    void updateEntity(@MappingTarget UserEntity userEntity, UserDto.Update updateDto);
}

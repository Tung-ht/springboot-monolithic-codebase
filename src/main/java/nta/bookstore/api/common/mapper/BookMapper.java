package nta.bookstore.api.common.mapper;

import nta.bookstore.api.dto.BookDto;
import nta.bookstore.api.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookEntity toEntity(BookDto.Save dto);

    BookDto toDto(BookEntity entity);

    List<BookDto> toDtos(List<BookEntity> entities);
}

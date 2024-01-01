package nta.bookstore.api.service;

import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookService {
    BookDto getBookById(Long id);

    List<BookDto> searchBooks(BookDto.SearchParam searchParam);

    BookDto saveBook(BookDto.Save bookDto);

    void deleteBookById(Long id);

    Map<ECategory, String> getAllCategories();
}

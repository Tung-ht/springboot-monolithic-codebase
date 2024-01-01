package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.BookDto;
import nta.bookstore.api.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/categories")
    public AppResponse<Map<ECategory, String>> getAllCategories() {
        return AppResponse.ok(bookService.getAllCategories());
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public AppResponse<BookDto> saveBook(@ModelAttribute BookDto.Save dto) {
        return AppResponse.ok(bookService.saveBook(dto));
    }

    @GetMapping("/{id}")
    public AppResponse<BookDto> getBookById(@PathVariable("id") Long bookId) {
        return AppResponse.ok(bookService.getBookById(bookId));
    }

    @DeleteMapping("/{id}")
    public AppResponse<?> deleteBookById(@PathVariable("id") Long bookId) {
        bookService.deleteBookById(bookId);
        return AppResponse.ok();
    }

    @GetMapping
    public AppResponse<List<BookDto>> searchBooks(@ModelAttribute BookDto.SearchParam searchParam) {
        return AppResponse.ok(bookService.searchBooks(searchParam));
    }
}

package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.constant.CommonConst;
import nta.bookstore.api.common.constant.ResponseConst;
import nta.bookstore.api.common.enumtype.ECategory;
import nta.bookstore.api.common.enumtype.EStatus;
import nta.bookstore.api.common.exception.AppException;
import nta.bookstore.api.common.mapper.BookMapper;
import nta.bookstore.api.common.utils.DataUtils;
import nta.bookstore.api.dto.BookDto;
import nta.bookstore.api.entity.BookEntity;
import nta.bookstore.api.repository.BookRepository;
import nta.bookstore.api.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ImageService imageService;
    private final BookMapper bookMapper;

    @Override
    public BookDto getBookById(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ResponseConst.NOT_FOUND_CODE, ResponseConst.BOOK_NOT_FOUND));
        BookDto dto = bookMapper.toDto(book);
        dto.setImageBase64Src(imageService.loadImageAsBase64Source(book.getImgUrl()));
        return dto;
    }

    @Override
    public Page<BookDto> searchBooks(BookDto.SearchParam searchParam) {
        Pageable pageable = DataUtils.mapPageDtoToPageable(searchParam.getPage(), searchParam.getSize());
        return bookRepository.searchBooks(searchParam.getKeyword(), searchParam.getCategory(), pageable, EStatus.ACTIVE.value)
                .map(book -> {
                    BookDto dto = bookMapper.toDto(book);
                    dto.setImageBase64Src(imageService.loadImageAsBase64Source(book.getImgUrl()));
                    return dto;
                });
    }

    @Override
    public BookDto saveBook(BookDto.Save bookDto) {
        BookEntity entity = bookMapper.toEntity(bookDto);
        entity.setIsActive(true);
        bookRepository.save(entity);
        String imageName = imageService.uploadImage(bookDto.getImgFile(), CommonConst.BOOK_IMAGE_PREFIX + entity.getId());
        entity.setImgUrl(imageName);
        bookRepository.save(entity);
        BookDto dto = bookMapper.toDto(entity);
        dto.setImageBase64Src(imageService.loadImageAsBase64Source(entity.getImgUrl()));
        return dto;
    }

    @Override
    public void deleteBookById(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException(ResponseConst.NOT_FOUND_CODE, ResponseConst.BOOK_NOT_FOUND));
//        bookRepository.delete(book);
//        imageService.deleteImage(book.getImgUrl());
        book.setIsActive(false);
        bookRepository.save(book);
    }

    @Override
    public Map<ECategory, String> getAllCategories() {
        Map<ECategory, String> result = new HashMap<>();
        List.of(ECategory.values())
                .forEach(eCategory -> result.put(eCategory, eCategory.vietnamese));
        return result;
    }
}

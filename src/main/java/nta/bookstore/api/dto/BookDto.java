package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ECategory;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String imageBase64Src;
    private ECategory category;
    private Long soldQuantity;
    private Long remainingQuantity;
    private Double importingPrice;
    private Double sellingPrice;

    @Getter
    @Setter
    @Builder
    public static class Save {
        private Long id;
        private String title;
        private String author;
        private String description;
        private MultipartFile imgFile;
        private ECategory category;
        private Long soldQuantity;
        private Long remainingQuantity;
        private Double importingPrice;
        private Double sellingPrice;
    }

    @Getter
    @Setter
    @Builder
    public static class SearchParam {
        // title or author
        private String keyword;
        private ECategory category;
        private Integer page;
        private Integer size;
    }
}

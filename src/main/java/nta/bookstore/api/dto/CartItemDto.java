package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ECategory;

@Getter
@Setter
@Builder
public class CartItemDto {
    private Long CartItemId;
    private Long bookId;
    private String title;
    private String author;
    private String description;
    private String imageBase64Src;
    private ECategory category;
    private Long quantity;
    private Double sellingPrice;

    public static class Update {
        private Long cartItemId;
        private Long quantity;
    }

    public static class Create {
        private Long userId;
        private Long bookId;
    }
}

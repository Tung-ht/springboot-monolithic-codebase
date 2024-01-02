package nta.bookstore.api.dto;

import lombok.*;
import nta.bookstore.api.common.enumtype.ECategory;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long userId;
    private Long cartItemId;
    private Long bookId;
    private String title;
    private String author;
    private String description;
    private String imageBase64Src;
    private ECategory category;
    private Double sellingPrice;
    private Long quantity;

    @Getter
    @Setter
    public static class Create {
        private Long userId;
        private Long bookId;
    }

    @Getter
    @Setter
    public static class Update {
        private Long cartItemId;
        private Long quantity;

    }

    @Getter
    @Setter
    public static class Delete {
        private Long cartItemId;
    }
}

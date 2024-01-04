package nta.bookstore.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ECategory;

@Getter
@Setter
@Builder
public class OrderDetailDto {
    private Long orderId;
    private String title;
    private String author;
    private String description;
    private String imageBase64Src;
    private ECategory category;
    private Long quantity;
    private Double importingPrice;
    private Double sellingPrice;
}

package nta.bookstore.api.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ECategory;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    private Long soldQuantity;
    private Long remainingQuantity;
    private Double importingPrice;
    private Double sellingPrice;

    @Builder
    public BookEntity(String title, String author, String description, String imgUrl, ECategory category, Long soldQuantity, Long remainingQuantity, Double importingPrice, Double sellingPrice) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.imgUrl = imgUrl;
        this.category = category;
        this.soldQuantity = soldQuantity;
        this.remainingQuantity = remainingQuantity;
        this.importingPrice = importingPrice;
        this.sellingPrice = sellingPrice;
    }
}

package nta.bookstore.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.ECategory;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity {
    private String title;
    private String author;
    private String description;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    private Long soldQuantity;
    private Long remainingQuantity;
    private Double importingPrice;
    private Double sellingPrice;
}

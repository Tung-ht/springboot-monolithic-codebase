package nta.bookstore.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nta.bookstore.api.common.enumtype.ECategory;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
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
    private Boolean isActive;
}

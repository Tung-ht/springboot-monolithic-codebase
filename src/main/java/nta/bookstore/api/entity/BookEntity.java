package nta.bookstore.api.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
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
    private String category;
    private Long soldQuantity;
    private Long remainingQuantity;
    private Double importingPrice;
    private Double sellingPrice;
}

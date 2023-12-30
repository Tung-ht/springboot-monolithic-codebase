package nta.bookstore.api.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetailEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    private Long quantity;
    private Double importingPrice;
    private Double sellingPrice;

    @Builder
    public OrderDetailEntity(OrderEntity order, BookEntity book, Long quantity, Double importingPrice, Double sellingPrice) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.importingPrice = importingPrice;
        this.sellingPrice = sellingPrice;
    }
}

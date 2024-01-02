package nta.bookstore.api.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shopping_cart_item")
public class CartItemEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    private Long quantity;
}

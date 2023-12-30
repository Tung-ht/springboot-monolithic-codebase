package nta.bookstore.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nta.bookstore.api.common.enumtype.EOrderStatus;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // can be different with userInfo
    private String fullName;
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;

    @Builder
    public OrderEntity(UserEntity user, String fullName, String phone, String address, EOrderStatus orderStatus) {
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.orderStatus = orderStatus;
    }
}

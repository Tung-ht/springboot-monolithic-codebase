package nta.bookstore.api.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nta.bookstore.api.common.enumtype.ENotifications;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private ENotifications type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;
    private Long fromUserId;
    private String message;
    private Boolean isRead;
}

package nta.bookstore.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private CommentEntity parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    private List<CommentEntity> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "reply_to_user_id")
    private UserEntity replyToUser;

    private String content;

    @Builder.Default
    private Boolean isActive = true;
}

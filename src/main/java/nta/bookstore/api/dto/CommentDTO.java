package nta.bookstore.api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private List<CommentDTO> children;
    private Long bookId;
    private Long replyToUserId; //user_id
    private String replyToFullName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Getter
    @Setter
    public static class SaveReq {
        private Long id;
        private Long bookId;
        private Long parentCommentId;
        private Long replyToUserId;
        private String content;
    }
}

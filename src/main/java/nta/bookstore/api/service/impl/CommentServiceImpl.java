package nta.bookstore.api.service.impl;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.enumtype.ENotifications;
import nta.bookstore.api.common.exception.NotFoundException;
import nta.bookstore.api.dto.CommentDTO;
import nta.bookstore.api.entity.BookEntity;
import nta.bookstore.api.entity.CommentEntity;
import nta.bookstore.api.entity.NotificationEntity;
import nta.bookstore.api.entity.UserEntity;
import nta.bookstore.api.repository.BookRepository;
import nta.bookstore.api.repository.CommentRepository;
import nta.bookstore.api.repository.NotificationRepository;
import nta.bookstore.api.repository.UserRepository;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final NotificationRepository notificationRepository;

    @Transactional
    @Override
    public List<CommentDTO> getListCommentsByBook(Long bookId) {
        // Get all lv1 comments for the book
        List<CommentEntity> comments = commentRepository.findByBookIdAndParentNull(bookId);

        // Convert to DTOs with nested replies
        return comments.stream()
                .map(this::convertToCommentDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveComment(AuthUserDetails authUserDetails, CommentDTO.SaveReq saveReq) {
        CommentEntity comment = new CommentEntity();

        // If updating existing comment
        if (saveReq.getId() != null) {
            comment = commentRepository.findById(saveReq.getId())
                    .orElseThrow(() -> new NotFoundException(CommentEntity.class));
            comment.setContent(saveReq.getContent());
        } else { // create new

            BookEntity book = bookRepository.findById(saveReq.getBookId())
                    .orElseThrow(() -> new NotFoundException(BookEntity.class));
            comment.setBook(book);

            // Set parent comment if it's a reply
            if (saveReq.getParentCommentId() != null) {
                CommentEntity parentComment = commentRepository.findById(saveReq.getParentCommentId())
                        .orElseThrow(() -> new NotFoundException(CommentEntity.class));
                comment.setParent(parentComment);
            }

            UserEntity user = userRepository.findActiveUserById(authUserDetails.getId());
            comment.setUser(user);

            if (!authUserDetails.getId().equals(saveReq.getReplyToUserId()) && saveReq.getReplyToUserId() != null) {
                UserEntity replyToUser = userRepository.findActiveUserById(saveReq.getReplyToUserId());
                comment.setReplyToUser(replyToUser);

                // If the comment is replied by other users -> create notification to the commenter
                NotificationEntity newNotification = new NotificationEntity();
                newNotification.setType(ENotifications.COMMENT);
                newNotification.setBook(book);
                newNotification.setFromUserId(user.getId());
                newNotification.setUser(replyToUser);
                newNotification.setMessage(ENotifications.getNotificationMessage(ENotifications.COMMENT, user.getFullName()));
                newNotification.setIsRead(false);
                notificationRepository.save(newNotification);
            }

            comment.setContent(saveReq.getContent());
        }

        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(Long commentId) {
        CommentEntity comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        // Soft delete
        comment.setIsActive(false);
        commentRepository.save(comment);
    }

    private CommentDTO convertToCommentDTO(CommentEntity comment) {
        List<CommentDTO> childComments = comment.getChildren().stream()
                .filter(CommentEntity::getIsActive)
                .map(this::convertToCommentDTO)
                .collect(Collectors.toList());

        CommentDTO dto = CommentDTO.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .fullName(comment.getUser().getFullName())
                .children(childComments)
                .bookId(comment.getBook().getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
        if (comment.getReplyToUser() != null) {
            dto.setReplyToUserId(comment.getReplyToUser().getId());
            dto.setReplyToFullName(comment.getReplyToUser().getFullName());
        }

        if (comment.getParent() != null) {
            dto.setParentCommentId(comment.getParent().getId());
        }
        return dto;
    }
}

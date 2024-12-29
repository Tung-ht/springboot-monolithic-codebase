package nta.bookstore.api.service;

import nta.bookstore.api.dto.CommentDTO;
import nta.bookstore.api.security.AuthUserDetails;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getListCommentsByBook(Long bookId);

    void saveComment(AuthUserDetails authUserDetails, CommentDTO.SaveReq saveReq);

    void deleteComment(Long commentId);
}

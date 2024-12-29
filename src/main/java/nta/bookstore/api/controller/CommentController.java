package nta.bookstore.api.controller;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.dto.AppResponse;
import nta.bookstore.api.dto.CommentDTO;
import nta.bookstore.api.security.AuthUserDetails;
import nta.bookstore.api.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public AppResponse<List<CommentDTO>> getListCommentsByBook(@RequestParam Long bookId) {
        return AppResponse.ok(commentService.getListCommentsByBook(bookId));
    }

    @PostMapping
    public AppResponse<?> saveComment(@AuthenticationPrincipal AuthUserDetails authUserDetails,
                                      @RequestBody CommentDTO.SaveReq saveReq) {
        commentService.saveComment(authUserDetails, saveReq);
        return AppResponse.ok();
    }

    @DeleteMapping("/{commentId}")
    public AppResponse<?> deleteComment(@PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return AppResponse.ok();
    }
}

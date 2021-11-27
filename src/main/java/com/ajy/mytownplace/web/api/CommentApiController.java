package com.ajy.mytownplace.web.api;

import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.domain.comment.Comment;
import com.ajy.mytownplace.service.CommentService;
import com.ajy.mytownplace.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Comment comment = commentService.writeComment(commentDto.getContent(), commentDto.getReviewId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 쓰기 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> commentDelete(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 삭제 성공", null), HttpStatus.OK);
    }
}

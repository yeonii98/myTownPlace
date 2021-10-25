package com.ajy.myTownPlace.web.api;

import com.ajy.myTownPlace.web.dto.CMRespDto;
import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.domain.comment.Comment;
import com.ajy.myTownPlace.service.CommentService;
import com.ajy.myTownPlace.web.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> commentSave(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CommentDto commentDto){
        Comment comment = commentService.writeComment(commentDto.getContent(), commentDto.getReviewId(), principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 쓰기 성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<?> commentDelete(@PathVariable int commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new CMRespDto<>(1,"댓글 삭제 성공", null), HttpStatus.OK);
    }
}

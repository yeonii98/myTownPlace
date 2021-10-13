package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.review.Review;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.service.ReviewService;
import com.cos.photogramstart.service.StoryService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;
    private final LikesService likesService;

    @GetMapping("/api/review")
    public ResponseEntity<?> story(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 5) Pageable pageable){
        Page<Review> reviews = reviewService.reviewList(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", reviews), HttpStatus.OK);
    }

    @PostMapping("/api/review/{reviewId}/likes")
    public ResponseEntity<?> likes(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int reviewId){
        likesService.likes(principalDetails.getUser().getId(), reviewId);
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/review/{reviewId}/likes")
    public ResponseEntity<?> unlikes(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int reviewId){
        likesService.unlikes(principalDetails.getUser().getId(), reviewId);
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 취소 성공", null), HttpStatus.OK);
    }
}

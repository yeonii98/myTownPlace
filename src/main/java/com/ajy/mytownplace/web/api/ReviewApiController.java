package com.ajy.mytownplace.web.api;


import com.ajy.mytownplace.domain.review.Review;
import com.ajy.mytownplace.service.ReviewService;
import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/myReview")
    public ResponseEntity<?> myStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 5) Pageable pageable){
        Page<Review> reviews = reviewService.myReviewList(principalDetails.getUser().getId(), pageable);
        if(reviews == null) return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 실패", null), HttpStatus.BAD_REQUEST);

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


    @GetMapping("/api/{apiId}/review")
    public ResponseEntity<?> reviewList(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 5) Pageable pageable, @PathVariable String apiId){
        Page<Review> reviews = reviewService.apiReviewList(principalDetails.getUser().getId(), apiId, pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", reviews), HttpStatus.OK);
    }

    @DeleteMapping("user/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int reviewId){
        System.out.println(reviewId);
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 삭제 성공", null), HttpStatus.OK);
    }
}

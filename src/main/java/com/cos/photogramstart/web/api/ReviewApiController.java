package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.review.Review;
import com.cos.photogramstart.kakao.dto.KakaoSearchLocalRes;
import com.cos.photogramstart.service.ReviewService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.review.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/api/story/{page}")
    public ResponseEntity<?> reviewStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int page){
        ArrayList<StoryDto> reviews = reviewService.listReview(principalDetails.getUser().getLocation(), page);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", reviews), HttpStatus.OK);
    }
}

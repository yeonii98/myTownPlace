package com.ajy.myTownPlace.web;

import com.ajy.myTownPlace.handler.ex.CustomValidationException;
import com.ajy.myTownPlace.web.dto.review.ReviewUploadDto;
import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping({"/review"})
    public String story(){
        return "image/review";
    }

    @PostMapping("/image")
    public String imageUpload(ReviewUploadDto reviewUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(reviewUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        //서비스 호출
        System.out.println(reviewUploadDto.getRating());
        reviewService.uploadReview(reviewUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

}

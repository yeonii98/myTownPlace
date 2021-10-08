package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ReviewService;
import com.cos.photogramstart.service.StoryService;
import com.cos.photogramstart.web.dto.review.ReviewUploadDto;
import com.cos.photogramstart.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
        reviewService.uploadReview(reviewUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

}

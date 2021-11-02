package com.ajy.myTownPlace.web;

import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.domain.user.UserRepository;
import com.ajy.myTownPlace.handler.ex.CustomValidationApiException;
import com.ajy.myTownPlace.handler.ex.CustomValidationException;
import com.ajy.myTownPlace.web.dto.review.ApiReviewDto;
import com.ajy.myTownPlace.web.dto.review.ReviewUploadDto;
import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

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

    @PostMapping("/apiReview")
    public String apiReviewUpload(ReviewUploadDto reviewUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(reviewUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }
        //서비스 호출
        System.out.println(reviewUploadDto.getApiId());
        reviewService.uploadReview(reviewUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

    @GetMapping("/apiReview/upload/{apiId}/{placeName}")
    public String apiReviewUpload(Model model, @PathVariable String apiId, @PathVariable String placeName, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User userEntity = userRepository.findById(principalDetails.getUser().getId()).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });
        model.addAttribute("apiId", apiId);
        model.addAttribute("placeName", placeName);
        model.addAttribute("location", userEntity.getLocation());
        return "image/apiReviewUpload";
    }

    @GetMapping("/apiReview/upload/{apiId}/{placeName}/{location}")
    public String apiSearchReviewUpload(Model model, @PathVariable String apiId, @PathVariable String placeName, @PathVariable String location){
        model.addAttribute("apiId", apiId);
        model.addAttribute("placeName", placeName);
        model.addAttribute("location", location);
        return "image/apiReviewUpload";
    }

    @GetMapping("/apiReview/{apiId}")
    public String apiReview(@PathVariable String apiId, Model model, Pageable pageable){
        ApiReviewDto dto = reviewService.apiReviewDto(apiId,pageable);
        model.addAttribute("apiId", apiId);
        model.addAttribute("dto",dto);
        return "image/apiReview";
    }
}

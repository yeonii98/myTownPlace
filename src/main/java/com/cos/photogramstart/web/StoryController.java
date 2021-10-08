package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.StoryService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.review.ReviewUploadDto;
import com.cos.photogramstart.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class StoryController {

    private final StoryService storyService;

    @GetMapping({"/"})
    public String story(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(required = false, defaultValue = "1") int page){
        ArrayList<StoryDto> dto =  storyService.listStory(principalDetails.getUser().getId(), principalDetails.getUser().getLocation(),page);
        model.addAttribute("u", dto);
        model.addAttribute("page", page);
        return "image/story";
    }

    @GetMapping("/search")
    public String searchStory(@RequestParam(value = "location") String location, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(required = false, defaultValue = "1") int page){
        ArrayList<StoryDto> dto = storyService.listStory(principalDetails.getUser().getId(), location, page);
        model.addAttribute("u", dto);
        model.addAttribute("location", location);
        model.addAttribute("page", page);
        return "image/search";
    }

    @GetMapping("/image/popular")
    public String popular(){
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload(){
        return "image/upload";
    }

//    @PostMapping("/image")
//    public String imageUpload(ReviewUploadDto reviewUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        if(reviewUploadDto.getFile().isEmpty()){
//            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
//        }
//        //서비스 호출
//        reviewService.uploadReview(reviewUploadDto, principalDetails);
//        return "redirect:/user/" + principalDetails.getUser().getId();
//    }
}

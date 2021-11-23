package com.ajy.myTownPlace.web;

import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.domain.user.UserRepository;
import com.ajy.myTownPlace.handler.ex.CustomValidationApiException;
import com.ajy.myTownPlace.handler.ex.CustomValidationException;
import com.ajy.myTownPlace.service.StoryService;
import com.ajy.myTownPlace.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@Controller
public class StoryController {

    private final StoryService storyService;


    @GetMapping({"/"})
    public String story(){
//        ArrayList<StoryDto> dto =  storyService.listStory(principalDetails.getUser().getId(), principalDetails.getUser().getLocation(),page);
//        model.addAttribute("u", dto);
//        model.addAttribute("page", page);
        return "image/story";
    }

    @GetMapping("/search")
    public String searchStory(@RequestParam(value = "location") String location, Model model){
//        ArrayList<StoryDto> dto = storyService.listStory(principalDetails.getUser().getId(), location, page);
//        model.addAttribute("u", dto);
        model.addAttribute("location", location);
//        model.addAttribute("page", page);
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

}

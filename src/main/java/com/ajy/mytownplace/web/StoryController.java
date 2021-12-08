package com.ajy.mytownplace.web;

import com.ajy.mytownplace.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class StoryController {

    private final StoryService storyService;


    @GetMapping({"/"})
    public String story(){
        return "image/story";
    }

    @GetMapping("/search")
    public String searchStory(@RequestParam(value = "location") String location, Model model){
        model.addAttribute("location", location);
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

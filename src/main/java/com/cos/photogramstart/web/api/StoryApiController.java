package com.cos.photogramstart.web.api;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.service.StoryService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class StoryApiController {

    private final StoryService storyService;

    @GetMapping("/api/story/{page}")
    public ResponseEntity<?> story(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int page){
        ArrayList<StoryDto> reviews = storyService.listStory(principalDetails.getUser().getId(), principalDetails.getUser().getLocation(), page);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", reviews), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/api/search/{page}")
    public ResponseEntity<?> searchStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int page, String location){
        ArrayList<StoryDto> reviews = storyService.listStory(principalDetails.getUser().getId(), location, page);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", reviews), HttpStatus.OK);
    }


}

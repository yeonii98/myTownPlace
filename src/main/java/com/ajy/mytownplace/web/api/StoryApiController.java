package com.ajy.mytownplace.web.api;


import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.service.StoryService;
import com.ajy.mytownplace.web.dto.story.StoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class StoryApiController {

    private final StoryService storyService;

    @GetMapping("/api/story")
    public ResponseEntity<?> story(@AuthenticationPrincipal PrincipalDetails principalDetails,  @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false) String location){
        ArrayList<StoryDto> dto =  storyService.listStory(principalDetails.getUser().getId(), "풍무동", page);
        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", dto), HttpStatus.OK);
    }

//    @GetMapping("/api/search")
//    public ResponseEntity<?> searchStory(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam String location){
//        ArrayList<StoryDto> dto = storyService.listStory(principalDetails.getUser().getId(), location, page);
//        return new ResponseEntity<>(new CMRespDto<>(1,"글 불러오기 성공", dto), HttpStatus.OK);
//    }
}

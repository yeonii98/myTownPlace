package com.ajy.mytownplace.web.api;

import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FavoriteApiController {

    private final FavoriteService favoriteService;

    @PostMapping("/api/favorite/{id}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable String id, String name, String detailUrl, String img){
        favoriteService.favorite(principalDetails.getUser().getId(), id, name, detailUrl, img);
        return new ResponseEntity<>(new CMRespDto<>(1,"즐겨찾기 추가 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("/api/favorite/{id}")
    public ResponseEntity<?> unsubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable String id){
        favoriteService.unfavorite(principalDetails.getUser().getId(), id);
        return new ResponseEntity<>(new CMRespDto<>(1,"즐겨찾기 해제 성공", null), HttpStatus.OK);
    }

}

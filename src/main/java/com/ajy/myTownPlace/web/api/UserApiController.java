package com.ajy.myTownPlace.web.api;

import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.handler.ex.CustomValidationException;
import com.ajy.myTownPlace.service.FavoriteService;
import com.ajy.myTownPlace.web.dto.CMRespDto;
import com.ajy.myTownPlace.web.dto.favorite.FavoriteDto;
import com.ajy.myTownPlace.web.dto.subscribe.SubscribeDto;
import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import com.ajy.myTownPlace.service.SubscribeService;
import com.ajy.myTownPlace.service.UserService;
import com.ajy.myTownPlace.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;
    private final FavoriteService favoriteService;

    @PutMapping("api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.profileUserImage(principalId,profileImageFile);
        principalDetails.setUser(userEntity); // 세션 변경
        return new ResponseEntity<>(new CMRespDto<>(1,"프로필 사진 변경 성공",null), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<SubscribeDto> subscribeDtoList = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);

        return new ResponseEntity<>(new CMRespDto<>(1,"구독자 정보 리스트 가져오기 성공",subscribeDtoList), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/favorite")
    public ResponseEntity<?> favoriteList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<FavoriteDto> favoriteDtoList = favoriteService.favoriteList(principalDetails.getUser().getId(), pageUserId);
        System.out.println(favoriteDtoList.get(0));
        return new ResponseEntity<>(new CMRespDto<>(1,"즐겨찾기 리스트 가져오기 성공", favoriteDtoList), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, //꼭 @Valid가 적혀있는 다음 파라메터에 적어야함함
                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성검사 실패함",errorMap);
        }else{
            User userEntity = userService.updateUser(id,userUpdateDto.toEntity());
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1,"회원 수정 완료",userEntity); //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
        }


    }
}

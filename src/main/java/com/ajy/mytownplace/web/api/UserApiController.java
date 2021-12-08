package com.ajy.mytownplace.web.api;

import com.ajy.mytownplace.domain.user.User;
import com.ajy.mytownplace.service.*;
import com.ajy.mytownplace.web.dto.CMRespDto;
import com.ajy.mytownplace.web.dto.favorite.FavoriteDto;
import com.ajy.mytownplace.web.dto.subscribe.SubscribeDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
import com.ajy.mytownplace.web.dto.user.UserPwdUpdateDto;
import com.ajy.mytownplace.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;
    private final FavoriteService favoriteService;
    private final S3Service s3Service;
    private final S3Uploader s3Uploader;

    @PutMapping("api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlUpdate(@PathVariable int principalId, MultipartFile profileImageFile, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        String imgPath = s3Uploader.upload(profileImageFile,"test");
        User userEntity = userService.profileUserImage(principalId, imgPath);
        principalDetails.setUser(userEntity); // 세션 변경
        return new ResponseEntity<>(new CMRespDto<>(1, "프로필 사진 변경 성공", null), HttpStatus.OK);
    }

    @DeleteMapping("api/user/{principalId}/profileImageUrl")
    public ResponseEntity<?> profileImageUrlDelete(@PathVariable int principalId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.profileUserImageDelete(principalId);
        principalDetails.setUser(userEntity); // 세션 변경
        return new ResponseEntity<>(new CMRespDto<>(1, "프로필 사진 삭제 성공", null), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<SubscribeDto> subscribeDtoList = subscribeService.subscribeList(principalDetails.getUser().getId(), pageUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 가져오기 성공", subscribeDtoList), HttpStatus.OK);
    }

    @GetMapping("/api/user/{pageUserId}/favorite")
    public ResponseEntity<?> favoriteList(@PathVariable int pageUserId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<FavoriteDto> favoriteDtoList = favoriteService.favoriteList(principalDetails.getUser().getId(), pageUserId);
        System.out.println(favoriteDtoList.get(0));
        return new ResponseEntity<>(new CMRespDto<>(1, "즐겨찾기 리스트 가져오기 성공", favoriteDtoList), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Valid UserUpdateDto userUpdateDto,
                               BindingResult bindingResult, //꼭 @Valid가 적혀있는 다음 파라메터에 적어야함함
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        User userEntity = userService.updateUser(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "회원 수정 완료", userEntity); //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.
    }

    @PutMapping("/api/user/pwd/{id}")
    public CMRespDto<?> pwdUpdate(@PathVariable int id,
                                  @Valid UserPwdUpdateDto userPwdUpdateDto,
                                  BindingResult bindingResult, //꼭 @Valid가 적혀있는 다음 파라메터에 적어야함함
                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User userEntity = userService.updateUserPwd(id, userPwdUpdateDto);

        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "비밀번호 수정 완료", userEntity); //응답시에 userEntity의 모든 getter 함수가 호출되고 JSON으로 파싱하여 응답한다.

    }
}

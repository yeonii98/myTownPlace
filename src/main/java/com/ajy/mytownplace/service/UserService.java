package com.ajy.mytownplace.service;

import com.ajy.mytownplace.domain.favorite.FavoriteRepository;
import com.ajy.mytownplace.domain.user.User;
import com.ajy.mytownplace.domain.user.UserRepository;
import com.ajy.mytownplace.handler.ex.CustomException;
import com.ajy.mytownplace.web.dto.user.UserProfileDto;
import com.ajy.mytownplace.domain.subscribe.SubscribeRepository;
import com.ajy.mytownplace.handler.ex.CustomValidationApiException;
import com.ajy.mytownplace.web.dto.user.UserPwdUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final FavoriteRepository favoriteRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public User profileUserImage(int principalId, String imgPath) {
        User userEntity = userRepository.findById(principalId).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + profileImageFile.getOriginalFilename();
//
//        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
//        try{
//            Files.write(imageFilePath, profileImageFile.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        userEntity.setProfileImageUrl(imgPath);

        return userEntity;
    }

    @Transactional
    public User profileUserImageDelete(int principalId){
        User userEntity = userRepository.findById(principalId).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });

        userEntity.setProfileImageUrl(null);

        return userEntity;
    }

    @Transactional(readOnly = true)
    public UserProfileDto profileUser(int pageUserId, int principalId){
        UserProfileDto dto = new UserProfileDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
            throw new CustomException("해당 사용자는 존재하지 않습니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwner(pageUserId == principalId); // true면 페이지 주인, false면 주인 아님
        dto.setImageCount(userEntity.getReviews().size());
        dto.setSubscribeCount(subscribeRepository.mSubscribeCount(pageUserId));
        dto.setSubscribeState(subscribeRepository.mSubscribeState(principalId, pageUserId) == 1);
        dto.setFavoriteCount(favoriteRepository.mFavoriteCount(pageUserId));

        userEntity.getReviews().forEach((review)->{
            review.setLikeCount(review.getLikes().size());
            review.setCommentCount(review.getComments().size());
        });

       return dto;
    }

    @Transactional
    public User updateUser(int id, User user){
        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });
        // 1. get() : 무조건 찾았다. 걱정마, 2.orElseThrow() : 못찾았어ㅜ exception 발동시킬게
        // 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
        userEntity.setName(user.getName());
        userEntity.setUsername(user.getUsername());
        userEntity.setBio(user.getBio());
        userEntity.setPhone(user.getPhone());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setGender(user.getGender());
        userEntity.setLocation(user.getLocation());

        return userEntity;
    }

    @Transactional
    public User updateUserPwd(int id, UserPwdUpdateDto userPwdUpdateDto){
        User userEntity = userRepository.findById(id).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수 없는 id입니다.");
        });
        String rawPassword = userPwdUpdateDto.getPassword();
        String encPassword;
        if(bCryptPasswordEncoder.matches(rawPassword, userEntity.getPassword())){
            if(userPwdUpdateDto.getNewPassword().equals(userPwdUpdateDto.getNewPasswordConfirm())){
                rawPassword = userPwdUpdateDto.getNewPassword();
                encPassword = bCryptPasswordEncoder.encode(rawPassword);
                userEntity.setPassword(encPassword);
                userRepository.save(userEntity);
            }
            else{
                throw new CustomValidationApiException("비밀번호가 일치하지 않습니다.");
            }
        }else{
            throw new CustomValidationApiException("비밀번호를 다시 입력해주세요.");
        }
        return userEntity;
    }
}

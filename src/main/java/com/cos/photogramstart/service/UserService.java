package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Transactional(readOnly = true)
    public UserProfileDto profileUser(int pageUserId, int principalId, String principalLocation){
        UserProfileDto dto = new UserProfileDto();
        User userEntity = userRepository.findById(pageUserId).orElseThrow(()->{
            throw new CustomException("해당 사용자는 존재하지 않습니다.");
        });

        dto.setUser(userEntity);
        dto.setPageOwner(pageUserId == principalId); // true면 페이지 주인, false면 주인 아님
        dto.setImageCount(userEntity.getReviews().size());
        dto.setPageOwnerReviewer(principalLocation.isBlank());
        dto.setSubscribeCount(subscribeRepository.mSubscribeCount(pageUserId));
        dto.setSubscribeState(subscribeRepository.mSubscribeState(principalId, pageUserId) == 1);

        if(!userEntity.getLocation().isBlank())
            dto.setAccountType(true);//true면 홍보 계정, false면 리뷰 계정

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
}

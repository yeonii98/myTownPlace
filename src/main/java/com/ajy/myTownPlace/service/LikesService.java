package com.ajy.myTownPlace.service;

import com.ajy.myTownPlace.domain.likes.LikesRepository;
import com.ajy.myTownPlace.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public void likes(int fromUserId, int toReviewId){
        try{
            likesRepository.mLikes(fromUserId,toReviewId);
        } catch (Exception e){
            throw new CustomApiException("이미 추가 하셨습니다."+e.getMessage());
        }
    }

    @Transactional
    public void unlikes(int fromUserId, int toReviewId){
        likesRepository.mUnLikes(fromUserId,toReviewId);
    }

}

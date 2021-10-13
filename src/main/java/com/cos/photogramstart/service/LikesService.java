package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.favorite.FavoriteRepository;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.likes.LikesRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.favorite.FavoriteDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

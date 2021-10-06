package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.favorite.FavoriteRepository;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.favorite.FavoriteDto;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final EntityManager em; //Repository는 EntityManager를 구현해서 만들어져 있는 구현체

    @Transactional(readOnly = true)
    public List<FavoriteDto> favoriteList(int principalId, int pageUserId){

        //쿼리 준비
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT f.toPlaceId, f.name, f.img, f.detailUrl, ");
        sb.append("(SELECT 1 FROM favorite WHERE fromUserId = ? AND toPlaceId = f.toPlaceId) as favoriteState ");
        sb.append("FROM favorite f ");
        sb.append("WHERE f.fromUserId = ?");//세미콜론 첨부하면 안 됨

        //쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, pageUserId);

        //쿼리 실행 - qlrm 라이브러리 필요, DTO에 DB결과를 매핑하기 위해서
        JpaResultMapper result = new JpaResultMapper();

        return result.list(query, FavoriteDto.class);
    }

    @Transactional
    public void favorite(int fromUserId, String toPlaceId){
        try{
            favoriteRepository.mFavorite(fromUserId,toPlaceId);
        } catch (Exception e){
            throw new CustomApiException("이미 추가 하셨습니다."+e.getMessage());
        }
    }

    @Transactional
    public void unfavorite(int fromUserId, String toPlaceId){
        favoriteRepository.mUnFavorite(fromUserId,toPlaceId);
    }

}

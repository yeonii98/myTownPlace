package com.ajy.mytownplace.service;

import com.ajy.mytownplace.domain.favorite.FavoriteRepository;
import com.ajy.mytownplace.handler.ex.CustomApiException;
import com.ajy.mytownplace.web.dto.favorite.FavoriteDto;
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
        sb.append("SELECT f.toPlaceId, f.name, f.detailUrl, f.img, ");
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
    public void favorite(int fromUserId, String toPlaceId, String name, String detailUrl, String img){
        try{
            favoriteRepository.mFavorite(fromUserId,toPlaceId, name, detailUrl, img);
        } catch (Exception e){
            throw new CustomApiException("이미 추가 하셨습니다."+e.getMessage());
        }
    }

    @Transactional
    public void unfavorite(int fromUserId, String toPlaceId){
        favoriteRepository.mUnFavorite(fromUserId,toPlaceId);
    }

}

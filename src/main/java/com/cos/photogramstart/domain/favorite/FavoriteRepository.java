package com.cos.photogramstart.domain.favorite;

import com.cos.photogramstart.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC등록이 자동으로 된다.
public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO favorite(fromUserID, toPlaceId, name, detailUrl, img, createDate) VALUES(:fromUserId, :toPlaceId, :name, :detailUrl, :img, now())", nativeQuery = true)
    void mFavorite(int fromUserId, String toPlaceId, String name, String detailUrl, String img); // 성공했을 때 : 변경된 행의 개수가 리턴됨, 실패했을 때 : -1 리턴

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "DELETE FROM favorite WHERE fromUserId =:fromUserId AND toPlaceId =:toPlaceId", nativeQuery = true)
    void mUnFavorite(int fromUserId, String toPlaceId);

    @Query(value = "SELECT COUNT(*) FROM favorite WHERE fromUserId =:principalId", nativeQuery = true)
    int mFavoriteCount(int principalId);

    @Query(value = "SELECT COUNT(*) FROM favorite WHERE fromUserId =:principalId AND toPlaceId =:toPlaceId", nativeQuery = true)
    int mFavoriteState(int principalId, String toPlaceId);

    int countByToPlaceId(String toPlaceId);
}
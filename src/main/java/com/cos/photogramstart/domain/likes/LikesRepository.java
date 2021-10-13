package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC등록이 자동으로 된다.
public interface LikesRepository extends JpaRepository<Favorite,Integer> {

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO likes(fromUserID, toReviewId, createDate) VALUES(:fromUserId, :toReviewId, now())", nativeQuery = true)
    void mLikes(int fromUserId, int toReviewId); // 성공했을 때 : 변경된 행의 개수가 리턴됨, 실패했을 때 : -1 리턴

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "DELETE FROM likes WHERE fromUserId =:fromUserId AND toReviewId =:toReviewId", nativeQuery = true)
    void mUnLikes(int fromUserId, int toReviewId);

    @Query(value = "SELECT COUNT(*) FROM likes WHERE toReviewId =:reviewId", nativeQuery = true)
    int mLikesCount(int reviewId);

    @Query(value = "SELECT COUNT(*) FROM likes WHERE fromUserId =:principalId AND toReviewId =:toReviewId", nativeQuery = true)
    int mLikesState(int principalId, int toReviewId);

}
package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC등록이 자동으로 된다.
public interface SubscribeRepository extends JpaRepository<Subscribe,Integer> {

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO subscribe(fromUserID, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); // 성공했을 때 : 변경된 행의 개수가 리턴됨, 실패했을 때 : -1 리턴

    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "DELETE FROM subscribe WHERE fromUserId =:fromUserId AND toUserId =:toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId =:pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);

    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE fromUserId =:principalId AND toUserId =:pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

}

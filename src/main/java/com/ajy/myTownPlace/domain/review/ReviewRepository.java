package com.ajy.myTownPlace.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query(value = "SELECT * FROM review WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Review> mReview(int principalId, Pageable pageable);
}

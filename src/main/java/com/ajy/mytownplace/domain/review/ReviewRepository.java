package com.ajy.mytownplace.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query(value = "SELECT * FROM review WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Review> mReview(int principalId, Pageable pageable);

    @Query(value = "SELECT * FROM review WHERE userId =:principalId ORDER BY id DESC", nativeQuery = true)
    Page<Review> myReview(int principalId, Pageable pageable);

    @Query(value = "SELECT * FROM review WHERE apiId =:apiId ORDER BY id DESC", nativeQuery = true)
    Page<Review> mApiReview(String apiId, Pageable pageable);

    @Query(value = "SELECT * FROM review WHERE apiId =:apiId ORDER BY id DESC", nativeQuery = true)
    ArrayList<Review> mApiReviewList(String apiId);

    @Query(value = "SELECT * FROM review WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId =:principalId) ORDER BY id DESC", nativeQuery = true)
    ArrayList<Review> mReview(int principalId);
}

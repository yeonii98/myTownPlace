package com.ajy.myTownPlace.service;

import com.ajy.myTownPlace.domain.review.Review;
import com.ajy.myTownPlace.domain.review.ReviewRepository;
import com.ajy.myTownPlace.web.dto.review.ApiReviewDto;
import com.ajy.myTownPlace.web.dto.review.ReviewUploadDto;
import com.ajy.myTownPlace.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = true)
    public Page<Review> reviewList(int principalId, Pageable pageable){
        Page<Review> reviews = reviewRepository.mReview(principalId, pageable);

        reviews.forEach((review -> {
            review.setLikeCount(review.getLikes().size());
            review.setCommentCount(review.getComments().size());
            review.getLikes().forEach((likes -> {
                if(likes.getUser().getId() == principalId){
                    review.setLikeState(true);
                }
            }));
        }));

        return reviews;
    }

    @Transactional(readOnly = true)
    public Page<Review> apiReviewList(int principalId, String apiId, Pageable pageable){
        Page<Review> reviews = reviewRepository.mApiReview(apiId, pageable);
        if(reviews.getSize() == 0){
            return null;
        }
        reviews.forEach((review -> {
            review.setLikeCount(review.getLikes().size());
            review.setCommentCount(review.getComments().size());
            review.getLikes().forEach((likes -> {
                if(likes.getUser().getId() == principalId){
                    review.setLikeState(true);
                }
            }));
        }));

        return reviews;
    }

    private double sum;
    private double count;
    @Transactional(readOnly = true)
    public ApiReviewDto apiReviewDto(String apiId, Pageable pageable){
        Page<Review> reviews = reviewRepository.mApiReview(apiId, pageable);
        ApiReviewDto apiReviewDto = new ApiReviewDto();
        if(reviews.isEmpty()) return null;

        sum = 0;
        count = 0;
        reviews.forEach((review -> {
            sum += review.getRating();
            count++;
        }));

        String placeName = reviews.get().findFirst().get().getPlace();
        String location = reviews.get().findFirst().get().getTown();

        apiReviewDto.setAvgRating(sum / count);
        apiReviewDto.setPlaceName(placeName);
        apiReviewDto.setLocation(location);

        return apiReviewDto;
    }

    //트랜잭션이란? 일의 최소 단위. ex) 송금을 하기 위해서는 입금과 출금 2가지 상황이 필요함, 입금과 출금을 성공 했을 때 commit을 한다. 실패하면 rollback
    //데이터의 변형이 일어난다면 Transactional을 꼭 걸어줘야한다.
    @Transactional
    public void uploadReview(ReviewUploadDto reviewUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + reviewUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        //통신 I/O -> 예외가 발생할 수 있다.
        try{
            Files.write(imageFilePath,reviewUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //review 테이블에 저장
        Review review = reviewUploadDto.toEntity(imageFileName, principalDetails.getUser());
        reviewRepository.save(review);

//        System.out.println(reviewEntity);
    }

    @Transactional
    public void deleteReview(int reviewId){
        reviewRepository.deleteById(reviewId);
    }
}
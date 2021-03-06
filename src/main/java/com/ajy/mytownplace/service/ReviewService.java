package com.ajy.mytownplace.service;

import com.ajy.mytownplace.domain.review.Review;
import com.ajy.mytownplace.domain.review.ReviewRepository;
import com.ajy.mytownplace.handler.ex.CustomValidationApiException;
import com.ajy.mytownplace.web.dto.review.ApiReviewDto;
import com.ajy.mytownplace.web.dto.review.ReviewUploadDto;
import com.ajy.mytownplace.config.auth.PrincipalDetails;
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
    public Page<Review> myReviewList(int principalId, Pageable pageable){
        Page<Review> reviews = reviewRepository.myReview(principalId, pageable);

        if(reviews.get().findFirst().isEmpty()) return null;

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

        apiReviewDto.setAvgRating(Double.parseDouble(String.format("%.1f", sum / count)));
        apiReviewDto.setPlaceName(placeName);
        apiReviewDto.setLocation(location);

        return apiReviewDto;
    }

    //??????????????????? ?????? ?????? ??????. ex) ????????? ?????? ???????????? ????????? ?????? 2?????? ????????? ?????????, ????????? ????????? ?????? ?????? ??? commit??? ??????. ???????????? rollback
    //???????????? ????????? ??????????????? Transactional??? ??? ??????????????????.
    @Transactional
    public void uploadReview(ReviewUploadDto reviewUploadDto, PrincipalDetails principalDetails, String imgPath){
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + reviewUploadDto.getFile().getOriginalFilename();
//
//        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
//
//        //?????? I/O -> ????????? ????????? ??? ??????.
//        try{
//            Files.write(imageFilePath,reviewUploadDto.getFile().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //review ???????????? ??????
        Review review = reviewUploadDto.toEntity(imgPath, principalDetails.getUser());
        reviewRepository.save(review);

    }

    @Transactional
    public void updateReview(ReviewUploadDto reviewUploadDto, int reviewId, String img){
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + reviewUploadDto.getFile().getOriginalFilename();
//
//        Path imageFilePath = Paths.get(uploadFolder+imageFileName);
//
//        //?????? I/O -> ????????? ????????? ??? ??????.
//        try{
//            Files.write(imageFilePath,reviewUploadDto.getFile().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(()-> {
            return new CustomValidationApiException("?????? ??? ?????? ????????????.");
        });

        reviewEntity.setCaption(reviewUploadDto.getCaption());
        reviewEntity.setRating(reviewUploadDto.getRating());
        reviewEntity.setPostImageUrl(img);
        if(!img.isBlank()){
            reviewEntity.setPostImageUrl(img);
        }
        reviewRepository.save(reviewEntity);
    }


    @Transactional
    public void deleteReview(int reviewId){
        reviewRepository.deleteById(reviewId);
    }

    @Transactional(readOnly = true)
    public ApiReviewDto getReviewDto(int reviewId){
        Review reviewEntity = reviewRepository.findById(reviewId).orElseThrow(()-> {
            return new CustomValidationApiException("?????? ??? ?????? id?????????.");
        });

        ApiReviewDto dto = new ApiReviewDto();
        dto.setLocation(reviewEntity.getTown());
        dto.setPlaceName(reviewEntity.getPlace());
        dto.setRating(reviewEntity.getRating());
        dto.setReviewImgUrl(reviewEntity.getPostImageUrl());
        dto.setCaption(reviewEntity.getCaption());

        return dto;
    }
}
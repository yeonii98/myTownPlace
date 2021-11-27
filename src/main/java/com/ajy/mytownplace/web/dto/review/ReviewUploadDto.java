package com.ajy.mytownplace.web.dto.review;

import com.ajy.mytownplace.domain.review.Review;
import com.ajy.mytownplace.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewUploadDto {
    private MultipartFile file;
    private String caption;
    private String place;
    private String town;
    private int rating;
    private String apiId;

    public Review toEntity(String postImageUrl, User user){
        return Review.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .town(town)
                .place(place)
                .user(user)
                .rating(rating)
                .apiId(apiId)
                .build();
    }
}

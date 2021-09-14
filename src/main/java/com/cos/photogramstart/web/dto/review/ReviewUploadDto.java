package com.cos.photogramstart.web.dto.review;

import com.cos.photogramstart.domain.review.Review;
import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Data
public class ReviewUploadDto {
    private MultipartFile file;
    private String caption;
    private String place;
    private String town;

    public Review toEntity(String postImageUrl, User user){
        return Review.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .town(town)
                .place(place)
                .user(user)
                .build();
    }
}

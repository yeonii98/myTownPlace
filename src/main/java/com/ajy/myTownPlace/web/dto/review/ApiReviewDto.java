package com.ajy.myTownPlace.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApiReviewDto {
    private double avgRating;
    private String placeName;
    private String location;
}

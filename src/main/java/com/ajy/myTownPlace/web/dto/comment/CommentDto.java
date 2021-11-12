package com.ajy.myTownPlace.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @NotBlank //빈값이거나 null체크
    private String content;
    @NotNull //빈값체크
    private int reviewId;
}

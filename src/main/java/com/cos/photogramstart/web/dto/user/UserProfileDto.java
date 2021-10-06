package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean pageOwner;
    private int imageCount;
    private User user;
    private boolean accountType;
    private boolean pageOwnerReviewer;

    private boolean subscribeState;
    private int subscribeCount;

    private int favoriteCount;
}

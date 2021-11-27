package com.ajy.mytownplace.web.dto.user;

import com.ajy.mytownplace.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

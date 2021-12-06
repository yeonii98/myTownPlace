package com.ajy.mytownplace.domain.likes;

import com.ajy.mytownplace.domain.review.Review;
import com.ajy.mytownplace.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "lieks",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"toReviewId","fromUserId"}
                )
        }
)
public class Likes { // N
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "toReviewId")
    @ManyToOne
    private Review review; // 1

    @JsonIgnoreProperties({"reviews"})
    @JoinColumn(name = "fromUserId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

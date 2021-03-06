package com.ajy.mytownplace.domain.comment;

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
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String content;

    @JsonIgnoreProperties({"reviews"})
    @JoinColumn(name = "userId")
    @ManyToOne//기본 패치 전략 : EAGER
    private User user;

    @JoinColumn(name = "reviewId")
    @ManyToOne//기본 패치 전략 : EAGER
    private Review review;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

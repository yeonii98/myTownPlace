package com.ajy.myTownPlace.domain.review;

import com.ajy.myTownPlace.domain.comment.Comment;
import com.ajy.myTownPlace.domain.likes.Likes;
import com.ajy.myTownPlace.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnoreProperties({"reviews"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)//이미지를 select하면 조인해서 User정보를 같이 들고옴
    private User user;

    private String postImageUrl;
    private String town;
    private String place;
    private String caption;

    //좋아요
    @JsonIgnoreProperties({"review"})
    @OneToMany(mappedBy = "review")//mappedBy : 난 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
    private List<Likes> likes;

    @Transient //DB에 칼럼이 만들어지지 않는다.
    private boolean likeState;

    @Transient //DB에 칼럼이 만들어지지 않는다.
    private int likeCount;

    //댓글
    @JsonIgnoreProperties({"review"})
    @OneToMany(mappedBy = "review")
    private List<Comment> comments;

    @Transient //DB에 칼럼이 만들어지지 않는다.
    private int commentCount;

   private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

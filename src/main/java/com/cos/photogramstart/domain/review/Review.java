package com.cos.photogramstart.domain.review;

import com.cos.photogramstart.domain.user.User;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)//이미지를 select하면 조인해서 User정보를 같이 들고옴
    private User user;

    private String postImageUrl;
    private String town;
    private String place;
    private String caption;

    //좋아요, 댓글글

   private LocalDateTime createDate;

    @PrePersist
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}

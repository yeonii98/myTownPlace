package com.cos.photogramstart.domain.user;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장할 수 있는 API를 제공)

import com.cos.photogramstart.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //디비에 테이블을 생성
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, length = 20)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website; //웹 사이트
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;
    private String location;

    private String profileImageUrl; //이미지
    private String role; //권한

    // 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼 만들지마.
    // User를 Select할 때 해당 User id로 등록된 review들을 다 가져온다.
    // Lazy = User를 Select할 때 해당 User id로 등록된 review들을 가져오지마 : default값 - 대신 getReviews() 함수의 review들이 호출 될 때 가져와!
    // Eager = User를 Select할 때 해당 User id로 등록된 review들을 전부 Join해서 가져와!!
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})//review의 user는 호출하지 마세영, review내부 user 호출 금지
    private List<Review> reviews;//양방향 매핑

    private LocalDateTime createDate;

    @PrePersist //디비에 INSERT 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

}

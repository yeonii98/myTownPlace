package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.review.Review;
import com.cos.photogramstart.domain.review.ReviewRepository;
import com.cos.photogramstart.web.dto.review.ReviewUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Value("${file.path}")
    private String uploadFolder;

    @Transactional(readOnly = true)//영속성 컨텍스트 변경 감지를 해서, 더티체킹, flush(반영) X
    public Page<Review> listReview(int principalId, Pageable pageable){
        Page<Review> reviewList = reviewRepository.mStory(principalId, pageable);
        return  reviewList;
    }

    //트랜잭션이란? 일의 최소 단위. ex) 송금을 하기 위해서는 입금과 출금 2가지 상황이 필요함, 입금과 출금을 성공 했을 때 commit을 한다. 실패하면 rollback
    //데이터의 변형이 일어난다면 Transactional을 꼭 걸어줘야한다.
    @Transactional
    public void uploadReview(ReviewUploadDto reviewUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + reviewUploadDto.getFile().getOriginalFilename();
        System.out.println("이미지 파일 이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        //통신 I/O -> 예외가 발생할 수 있다.
        try{
            Files.write(imageFilePath,reviewUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //review 테이블에 저장
        Review review = reviewUploadDto.toEntity(imageFileName, principalDetails.getUser());
        reviewRepository.save(review);

//        System.out.println(reviewEntity);
    }
}
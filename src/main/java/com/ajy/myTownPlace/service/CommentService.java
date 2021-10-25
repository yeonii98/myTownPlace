package com.ajy.myTownPlace.service;

import com.ajy.myTownPlace.domain.review.Review;
import com.ajy.myTownPlace.domain.comment.Comment;
import com.ajy.myTownPlace.domain.comment.CommentRepository;
import com.ajy.myTownPlace.domain.user.User;
import com.ajy.myTownPlace.domain.user.UserRepository;
import com.ajy.myTownPlace.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment writeComment(String content, int reviewId, int userId){

        //Tip (객체를 만들 때 id 값만 담아서 insert 할 수 있다)
        //대신 return 시에 image 객체와 user 객체는 id값만 가지고 있는 빈 객체를 리턴받는다.
        Review review = new Review();
        review.setId(reviewId);

        User userEntity = userRepository.findById(userId).orElseThrow(()->{
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setReview(review);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(int commentId){
        try{
            commentRepository.deleteById(commentId);
        } catch (Exception e){
            throw new CustomApiException(e.getMessage());
        }
    }

}

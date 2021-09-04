package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service //Ioc, 트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;

    @Transactional //Write(Insert, Update, Delete)에 트랜잭션
    public User join(User user){
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}

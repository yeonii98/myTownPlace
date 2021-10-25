package com.ajy.myTownPlace.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//어노테이션이 없어도 JpaRepository를 상속하면 IoC등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findByIdOrderByIdDesc(int id);
}

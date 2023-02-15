package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//DAO //JSP로 치면 DAO라고 생각하면 됨.
//자동으로 bean 등록이 된다.
//@Repository //생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{

}// class() end
package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//DAO //JSP로 치면 DAO라고 생각하면 됨.
//자동으로 bean 등록이 된다.
//@Repository //생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}// class() end

// JPA Naming 전략
// SELECT * FROM user WHERE username = ?1 AND password = ?2;
//User findByUsernameAndPassword(String username, String password);

// 위에 있는 것과 같은 결과. 둘 중 하나 택. 우리는 위에 있는 것으로 하겠다.
/*
 * @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2;",
 * nativeQuery = true) User login(String username, String password);
 */
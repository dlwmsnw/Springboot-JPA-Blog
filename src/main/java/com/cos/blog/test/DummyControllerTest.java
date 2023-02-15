package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController // 페이지를 이동할 것이 아니라 데이터만 리턴해주는 회원가입이 잘 됐다 안 됐다는 응답만 할 수 있게 하는 컨트롤러
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	//파마레터를 받을 필요가 없음. 전체를 다 받을 것이기 때문에.
	//http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	//한 페이지당 2건에 데이터를 리턴받아 볼 예정.
	//http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	// email, password
	//http://localhost:8000/blog/dummy/user/1
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : "+id);
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		return null;
	}
	
	// {id} 주소로 파마레터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/2
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/3을 찾으면 내가 데이터베이스에서 못 찾아오게 되면 user가 null이 될 거 아냐?
		// 그럼 return null이 리턴이 되잖아. 그럼 프로그램에 문제가 생겨.
		// Optional로 너의 User 객체를 감싸서 가져올 테니 null인지 아닌지 판단해서 return해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹 브라우저가 이해할 수 있는 데이터 -> Json
		// 예전 같은 경우는 Gson 같은 라이브러리를 사용해서 자바 오브젝트를 Json으로 변경해서 던져주는데,
		// 스프링부트는 = MessageConverter라는 애가 으답시에 자동 작동
		// user 오브젝트를 Json을 변환해서 브라우저에게 던져준다.
		return user;
	}

	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join") // 회원가입 insert 할 거니까 PostMapping 사용.
	public String join(User user) { // key = value (약속된 규칙)
		System.out.println("id : " + user.getId()); // 전송받는 것이 아니기 때문에 null. 근데 Id는 int 값이니까 디폴트 값으로 0.
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole()); // 전송받는 것이 아니기 때문에 null
		System.out.println("createDate : " + user.getCreateDate()); // 전송받는 것이 아니기 때문에 null

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}// class() end
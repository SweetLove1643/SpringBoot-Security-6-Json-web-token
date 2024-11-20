package vn.iostar.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.iostar.Entity.User;

@RestController
@EnableMethodSecurity
public class UserController {

	final List<User> user = List.of(
			User.builder().id("01").name("Phan Van Quan").email("phanuan028@gmail.com").build(),
			User.builder().id("02").name("Phan Van Quan").email("phanuan2004@gmail.com").build());
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("hello is Guest");
	}
	
	@GetMapping("/user/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getUserList(){
		List<User> list = this.user;
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<User> getUserList(@PathVariable String id){
		List<User> list = this.user.stream().filter(user -> user.getId().equals(id)).toList();
		return ResponseEntity.ok(list.get(0));
	}
}

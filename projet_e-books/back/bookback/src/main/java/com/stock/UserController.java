package com.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.User;
import repository.UserRepository;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/users")
	  public List<User> getAllUsers() {
	    System.out.println("Get all Users...");
	 
	    List<User> Users = new ArrayList<>();
	    userRepository.findAll().forEach(Users::add);
	 
	    return Users;
	  }
	@GetMapping("/users/{login}/{pwd}")
	public ResponseEntity<User> getUserById(@PathVariable String login, String pwd)
			throws ResourceNotFoundException {
		User User = userRepository.findByLogin(login)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this hello id :: " + login));
		return ResponseEntity.ok().body(User);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long UserId)
			throws ResourceNotFoundException {
		User User = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));
		return ResponseEntity.ok().body(User);
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Validated @RequestBody User User) {
		if(userRepository.existsByLogin(User.getLogin())) {
			Map<String,Object> body = new LinkedHashMap<>();
			body.put("message","Login existe déja");
			System.out.println("Enterrr");
			return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		}
		if(userRepository.existsByEmail(User.getEmail())) {
			Map<String,Object> body = new LinkedHashMap<>();
			body.put("message","Email deja existe");
			System.out.println("Enterrr");
			return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(userRepository.save(User),HttpStatus.OK);

	}
	

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long UserId)
			throws ResourceNotFoundException {
		User User = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found  id :: " + UserId));

		userRepository.delete(User);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/users/delete")
	  public ResponseEntity<String> deleteAllUsers() {
	    System.out.println("Delete All Users...");
	 
	    userRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Users have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/users/{id}")
	  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User User) {
	    System.out.println("Update User with ID = " + id + "...");
	 
	    Optional<User> UserInfo = userRepository.findById(id);
	 
	    if (UserInfo.isPresent()) {
	    	User user = UserInfo.get();
	           user.setAdresse(User.getAdresse());
	           user.setVille(User.getVille());
	           user.setCodep(User.getCodep());
	           user.setEmail(User.getEmail());
	           user.setLogin(User.getLogin());
	           user.setPwd(User.getPwd());
	           
	           
	      return new ResponseEntity<>(userRepository.save(User), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } 
	  }
	  
}
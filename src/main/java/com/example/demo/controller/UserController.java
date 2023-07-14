package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/v1/")

public class UserController {
	
	
	@Autowired
	private UserRepository userepository ;
	
	
	//get all the users 
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userepository.findAll();
	}
	
	
	//create employee rest api
	@PostMapping("/users")
	public User createUser (@RequestBody User user) {
		return userepository.save(user);
	}
	
	//get User by Id rest api
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById (@PathVariable Long id ) {
		
		User user = userepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id :"+id));
		return ResponseEntity.ok(user);	
	}
	
	
	// update employee rest api
	
		@PutMapping("/users/{id}")
		public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
			User user = userepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			
			user.setName(userDetails.getName()) ;
			user.setLastname(userDetails.getLastname());
			user.setEmail(userDetails.getEmail());
			
			User updatedUser = userepository.save(user);
			return ResponseEntity.ok(updatedUser);
		}
	
		
		// delete employee rest api
		@DeleteMapping("/users/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			User user = userepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			userepository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}	
	

}

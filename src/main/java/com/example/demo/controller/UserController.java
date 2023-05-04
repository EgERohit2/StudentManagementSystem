package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.dto.UserDtoImpl;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<?> postAllUsers(@RequestBody User user) {
		try {
			this.userService.postUser(user);
			return new ResponseEntity<>(new SuccessResponseDto("posted", "succeded", null), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Error ", "No data found"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping()
	public ResponseEntity<?> getAllUsers() {
		try {
			List<User> user = this.userService.getUser();
			return new ResponseEntity<>(new SuccessResponseDto("success", "succeded", user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Error ", "No data found"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/assignRoles")
	public ResponseEntity<?> assignRoles(@RequestParam(value = "user_id") int user_id,
			@RequestParam(value = "role_id") int role_id) {

		try {
			this.userService.addRoles(user_id, role_id);
			return new ResponseEntity<>(new SuccessResponseDto("success", "Role assigned", null), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Error ", "No data found"), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/getPagination")
	public ResponseEntity<?> pagination(@RequestParam(value = "search") String search,
			@RequestParam(value = "pageNumber") String pageNumber, @RequestParam(value = "pageSize") String pageSize) {
		Page<UserDtoImpl> cvs = userService.findAllWithPage(search, pageNumber, pageSize);
		if (cvs.getTotalElements() != 0) {
			return new ResponseEntity<>((cvs.getContent()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}

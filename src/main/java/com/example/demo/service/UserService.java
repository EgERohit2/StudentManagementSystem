package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserDtoImpl;
import com.example.demo.entities.User;

public interface UserService {

	public User postUser(User user);
	
	public List<User> getUser();
	
	public void addRoles(int userId, int roleId); 
	
	public List<UserDto> convertEntityToDto();
	
//	public Page<UserDto> getAllwithDto(int pageNumber, int pageSize);
	
	public Page<UserDtoImpl> findAllWithPage(String search, String pageNumber, String pageSize);
		
}

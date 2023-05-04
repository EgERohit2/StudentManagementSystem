package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserDtoImpl;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User postUser(User user) {
		User user1 = new User();
		user1.setEmail(user.getEmail());
		user1.setUsername(user.getUsername());
		String pass = user.getPassword();
		String s = passwordEncoder.encode(pass);
		user1.setPassword(s);
		return userRepository.save(user1);
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public void addRoles(int userId, int roleId) {
		User u = userRepository.findById(userId).orElseThrow();
		Role r = roleRepository.findById(roleId).orElseThrow();

		List<Role> userRole = u.getRole();
		userRole.add(r);
		u.setRole(userRole);
		userRepository.save(u);

	}

	@Override
	public List<UserDto> convertEntityToDto() {
		List<User> userList = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			UserDto userDto = new UserDto();
			userDto.setName(userList.get(i).getUsername());
			userDto.setEmail(userList.get(i).getEmail());
			userDto.setPassword(userList.get(i).getPassword());
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

//	@Override
//	public Page<UserDto> getAllwithDto(int pageNumber, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//		return new PageImpl<UserDto>(UserDto,pageable, pageSize);
//		return null;
//	}

	@Override
	public Page<UserDtoImpl> findAllWithPage(String search, String pageNumber, String pageSize) {
		Pageable paging = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
		Page<UserDtoImpl> cvList;
		if ((search == "") || (search == null) || (search.length() == 0)) {
			cvList = userRepository.findByOrderById(paging, UserDtoImpl.class);
		} else {
			cvList = userRepository.findByEmailContainingIgnoreCaseOrderById(StringUtils.trimAllWhitespace(search),
					paging, UserDtoImpl.class);
		}

		return cvList;
	}

}

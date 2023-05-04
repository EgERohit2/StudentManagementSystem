package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.UserDtoImpl;
import com.example.demo.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Page<UserDtoImpl> findByOrderById(Pageable paging, Class<UserDtoImpl> class1);

	Page<UserDtoImpl> findByEmailContainingIgnoreCaseOrderById(String trimAllWhitespace, Pageable paging,
			Class<UserDtoImpl> class1);

	User findByUsername(String username);

}

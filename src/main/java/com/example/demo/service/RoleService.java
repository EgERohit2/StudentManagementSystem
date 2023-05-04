package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Role;

public interface RoleService {

	public void postRoles(Role role);
	
	public List<Role> getRoles();
}

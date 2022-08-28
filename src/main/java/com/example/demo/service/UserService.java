package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userReposity;

	public List<User> getUsers(){
		return this.userReposity.findAll();
		}
	
	public User saveUser(User user) {
		User savedUser = userReposity.save(user);
		return savedUser;
	}
	
	public User updateUser(User user, Long id) {
		User currentUser = findUserById(id);
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmail(user.getEmail());
		return userReposity.save(currentUser);
	}
	
	public void deleteUser(Long id) {
		userReposity.deleteById(id);		
	}
	
	public User findUserById(Long id) {
		Optional<User> optionalUser = userReposity.findById(id);
		return optionalUser.get();
	}
}

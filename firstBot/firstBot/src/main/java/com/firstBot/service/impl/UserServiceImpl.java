package com.firstBot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstBot.entity.Genre;
import com.firstBot.entity.User;
import com.firstBot.repository.UserRepository;
import com.firstBot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public boolean remove(int id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public User findByMessengerUserId(String messengerUserId) {
		return userRepository.findByMessengerUserId(messengerUserId);
	}


}

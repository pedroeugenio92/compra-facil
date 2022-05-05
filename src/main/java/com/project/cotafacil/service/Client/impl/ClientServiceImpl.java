package com.project.cotafacil.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cotafacil.repository.user.UserRepository;
import com.project.cotafacil.model.Client.client;
import com.project.cotafacil.service.user.UserService;

@Service
public class ClientServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	@Override
	public User save(User user) {
		return repository.save(user);
	}
	
	@Override
	public Optional<User> findByMail(String mail) {
		return repository.findByMail(mail);
	}

}

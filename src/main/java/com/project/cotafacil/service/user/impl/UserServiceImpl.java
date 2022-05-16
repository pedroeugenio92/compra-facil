package com.project.cotafacil.service.user.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cotafacil.repository.user.UserRepository;
import com.project.cotafacil.exception.user.UserFoundException;
import com.project.cotafacil.model.user.User;
import com.project.cotafacil.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository repository;
		
	@Override
	public Optional<User> findById(int id){
		return repository.findById(id);
	}
	
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Page<User> findByExcludedFalsePageable(Pageable pg){
		return repository.findByExcludedFalse(pg);
	}
	
	@Override
	public User save(User user) throws UserFoundException {
		validadeUser(user);
		user.setPassword(getPasswordEncrypted(user.getPassword()));
		return repository.save(user);
	}
	
	@Override
	public User update(User user, User userFind){
		user.setPassword(getPasswordEncrypted(user.getPassword()));
		user.setActived(userFind.isActived());
		user.setExcluded(userFind.isExcluded());
		user.setCreationDate(userFind.getCreationDate());
		return repository.save(user);
	}
	
	@Override
	public void delete(User user) {
		user.setExcluded(true);
		repository.save(user);
	}
	
	@Override
	public Optional<User> findByMail(String mail) {
		return repository.findByMail(mail);
	}
	
	private void validadeUser(User user) throws UserFoundException {
		if(validateMail(user)) {
			throw new UserFoundException("Não foi possível salvar o usuário. Email já existente!");
		}
		if(validateCPF(user)) {
			throw new UserFoundException("Não foi possível salvar o usuário. CPF já existente!");
		}
	}
	
	private boolean validateMail(User user) {
		Optional<User> userMail = repository.findByMail(user.getMail());
		return userMail.isPresent();
	}
	
	private boolean validateCPF(User user) {
		Optional<User> userMail = repository.findByCpf(user.getCpf());
		return userMail.isPresent();
	}
	
	private String getPasswordEncrypted(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}

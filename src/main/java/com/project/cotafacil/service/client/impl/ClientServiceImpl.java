package com.project.cotafacil.service.client.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.cotafacil.repository.client.*;
import com.project.cotafacil.model.client.*;
import com.project.cotafacil.model.user.User;
import com.project.cotafacil.service.client.*;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository repository;
	
	@Override
	public Optional<Client> findById(int id){
		return repository.findById(id);
	}
	
	@Override
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	@Override
	public Client save(Client client) {
		return repository.save(client);
	}
	
	public Page<Client> findByExcludedFalsePageable(Pageable pg){
		return repository.findByExcludedFalse(pg);
	}

}

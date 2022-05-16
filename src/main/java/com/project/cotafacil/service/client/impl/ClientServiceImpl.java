package com.project.cotafacil.service.client.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cotafacil.repository.client.*;
import com.project.cotafacil.model.client.*;
import com.project.cotafacil.service.client.*;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	ClientRepository repository;
	
	@Override
	public Optional<Client> findById(Long id){
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
	
	@Override
	public Optional<Client> findByMail(String mail) {
		return repository.findByMail(mail);
	}

}

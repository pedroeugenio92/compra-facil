package com.project.cotafacil.service.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.cotafacil.model.client.*;
import com.project.cotafacil.model.user.User;

public interface ClientService {
	
	Optional<Client> findById(int id);

	List<Client> findAll();

	Client save(Client client);

	Page<Client> findByExcludedFalsePageable(Pageable pg);
	
	
}

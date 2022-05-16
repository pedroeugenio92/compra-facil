package com.project.cotafacil.repository.client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cotafacil.model.client.*;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByMail(String email);

}

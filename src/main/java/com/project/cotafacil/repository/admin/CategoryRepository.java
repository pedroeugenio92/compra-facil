package com.project.cotafacil.repository.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cotafacil.model.user.User;

public interface CategoryRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByMail(String email);
	
	Optional<User> findByCpf(String cpf);
	
	Page<User> findByExcludedFalse(Pageable pg);
}

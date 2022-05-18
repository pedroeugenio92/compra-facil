package com.project.cotafacil.service.provider;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.cotafacil.exception.user.UserFoundException;
import com.project.cotafacil.model.provider.*;

public interface ProviderService {
	
	Optional<Provider> findById(int id);

	List<Provider> findAll();

	Provider save(Provider provider) throws UserFoundException;
	
	Provider update(Provider provider, Provider providerFind);

	Optional<Provider> findByMail(String mail);
	
	Page<Provider> findByExcludedFalsePageable(Pageable pg);
	
	void delete(Provider provider);
	
	
}

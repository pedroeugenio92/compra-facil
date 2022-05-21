package com.project.cotafacil.service.provider.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.cotafacil.repository.provider.ProviderRepository;
import com.project.cotafacil.exception.provider.ProviderFoundException;
import com.project.cotafacil.model.provider.Provider;
import com.project.cotafacil.service.provider.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	
	@Autowired
	ProviderRepository repository;
		
	@Override
	public Optional<Provider> findById(int id){
		return repository.findById(id);
	}
	
	@Override
	public List<Provider> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Page<Provider> findByExcludedFalsePageable(Pageable pg){
		return repository.findByExcludedFalse(pg);
	}
	
	@Override
	public Provider save(Provider provider) throws ProviderFoundException {
		validadeProvider(provider);
		provider.setPassword(getPasswordEncrypted(provider.getPassword()));
		return repository.save(provider);
	}
	
	@Override
	public Provider update(Provider provider, Provider providerFind){
		provider.setPassword(getPasswordEncrypted(provider.getPassword()));
		provider.setActived(providerFind.isActived());
		provider.setExcluded(providerFind.isExcluded());
		provider.setCreationDate(providerFind.getCreationDate());
		return repository.save(provider);
	}
	
	@Override
	public void delete(Provider provider) {
		provider.setExcluded(true);
		repository.save(provider);
	}
	
	@Override
	public Optional<Provider> findByMail(String mail) {
		return repository.findByMail(mail);
	}
	
	private void validadeProvider(Provider provider) throws ProviderFoundException {
		if(validateMail(provider)) {
			throw new ProviderFoundException("Não foi possível salvar o usuário. Email já existente!");
		}
		if(validateCPF(provider)) {
			throw new ProviderFoundException("Não foi possível salvar o usuário. CPF já existente!");
		}
	}
	
	private boolean validateMail(Provider provider) {
		Optional<Provider> userMail = repository.findByMail(provider.getMail());
		return userMail.isPresent();
	}
	
	private boolean validateCPF(Provider provider) {
		Optional<Provider> userMail = repository.findByCpf(provider.getCpf());
		return userMail.isPresent();
	}
	
	private String getPasswordEncrypted(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}

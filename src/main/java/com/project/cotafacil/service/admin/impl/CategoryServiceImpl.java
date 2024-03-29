package com.project.cotafacil.service.admin.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.cotafacil.repository.admin.CategoryRepository;
import com.project.cotafacil.exception.admin.CategoryAlreadyExistingException;
import com.project.cotafacil.exception.admin.CategoryFoundException;
import com.project.cotafacil.exception.admin.CategoryInvalidUpdateException;
import com.project.cotafacil.exception.client.ClientInvalidUpdateException;
import com.project.cotafacil.exception.provider.ProviderAlreadyExistingException;
import com.project.cotafacil.exception.user.UserFoundException;
import com.project.cotafacil.exception.user.UserInvalidUpdateException;
import com.project.cotafacil.model.admin.Category;
import com.project.cotafacil.model.client.Client;
import com.project.cotafacil.model.provider.Provider;
import com.project.cotafacil.service.admin.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository repository;
		
	@Override
	public Optional<Category> findById(int id){
		return repository.findById(id);
	}
	
	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Page<Category> findByExcludedFalsePageable(Pageable pg){
		return repository.findByExcludedFalse(pg);
	}
	

	@Override
	public Category save(Category category) throws CategoryFoundException {
		validateCategory(category);
		return repository.save(category);
	}
	
	
	
	@Override
	public Category update(Category category) throws CategoryInvalidUpdateException{
		Category categoryFind = findCategory(category.getId());
		category.setExcluded(categoryFind.isExcluded());
		category.setCreationDate(categoryFind.getCreationDate());
		return repository.save(category);
	}
	
	@Override
	public void delete(Category category) {
		category.setExcluded(true);
		repository.save(category);
	}
	
	private void validateCategory(Category category) throws CategoryFoundException {
		if(validateDescription(category.getDescription())) {
			throw new CategoryFoundException("Não foi possível salvar a categoria. Descrição já existente!");
		}
	}
	
	private boolean validateDescription(String description) throws CategoryFoundException {
		Optional<Category> categoryDescription = repository.findByDescription(description);
		return categoryDescription.isEmpty();
	}
	
	private Category findCategory(int id) throws CategoryInvalidUpdateException {
		Optional<Category> category = findById(id);
		if(!category.isPresent()) {
			throw new CategoryInvalidUpdateException("Categoria não encontrada para realizar a atualização. ID:" + id);
		}
		return category.get();
	}
	
}

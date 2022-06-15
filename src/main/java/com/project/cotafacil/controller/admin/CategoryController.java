package com.project.cotafacil.controller.admin;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cotafacil.exception.admin.CategoryFoundException;
import com.project.cotafacil.exception.admin.CategoryInvalidUpdateException;
import com.project.cotafacil.model.admin.Category;
import com.project.cotafacil.model.dto.admin.CategoryDTO;
import com.project.cotafacil.model.dto.response.Response;
import com.project.cotafacil.model.dto.admin.CategoryRequestDTO;
import com.project.cotafacil.model.dto.admin.CategoryUpdateDTO;
import com.project.cotafacil.service.admin.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	@ApiOperation(value = "Rota que busca todos os categoria ativos", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Response<Page<CategoryDTO>>> findAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable) throws CategoryFoundException{
		Response<Page<CategoryDTO>> response = new Response<>();
		
		Page<Category> findCategory = service.findByExcludedFalsePageable(pageable);
		
		if(findCategory.isEmpty()) {
			throw new CategoryFoundException("Nenhuma categoria encontrada!");
		}
		
		Page<CategoryDTO> categorys = findCategory.map(u -> u.convertEntityToDTO());
		
		response.setData(categorys);
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	@ApiOperation(value = "Rota que busca uma Categoria", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Response<CategoryDTO>> findById(@PathVariable Integer id) throws CategoryFoundException{
		Response<CategoryDTO> response = new Response<>();
		
		Optional<Category> category = service.findById(id);
		
		if(category.isEmpty()) {
			throw new CategoryFoundException("Categoria não encontrado");
		}
		
		Category categoryEntity = category.get();	
		response.setData(categoryEntity.convertEntityToDTO());
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "Rota que cria um novo categoria", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Response<CategoryDTO>> create(@Valid @RequestBody CategoryRequestDTO categoryDTO, BindingResult result) throws CategoryFoundException{
		Response<CategoryDTO> response = new Response<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Category category = categoryDTO.convertDTOToEntity();
		Category categoryToCreate = service.save(category);
		
		response.setData(categoryToCreate.convertEntityToDTO());
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "Rota que atualiza os dados do categoria", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Response<CategoryDTO>> update(@Valid @RequestBody CategoryUpdateDTO categoryDTO, BindingResult result) throws CategoryInvalidUpdateException, CategoryFoundException{
		Response<CategoryDTO> response = new Response<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Category category = categoryDTO.convertDTOToEntity();
		Category categoryToUpdate = service.update(category);
		
		response.setData(categoryToUpdate.convertEntityToDTO());
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value = "Rota que deleta um categoria", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Response<String>> delete(@PathVariable Integer id) throws CategoryFoundException{
		Response<String> response = new Response<>();
		
		Optional<Category> category = service.findById(id);
		if(category.isEmpty()) {
			throw new CategoryFoundException("Categoria não encontrado");
		}
		
		Category categoryEntity = category.get();	
		service.delete(categoryEntity);
		
		response.setData("Categoria deletado com sucesso!");
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}

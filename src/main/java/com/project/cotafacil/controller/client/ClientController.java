package com.project.cotafacil.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cotafacil.model.dto.response.Response;
import com.project.cotafacil.model.dto.user.UserDTO;
import com.project.cotafacil.model.user.User;
import com.project.cotafacil.model.dto.client.*;
import com.project.cotafacil.exception.client.ClientNotFoundException;
import com.project.cotafacil.exception.user.UserFoundException;
import com.project.cotafacil.model.client.*;
import com.project.cotafacil.service.client.*;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	private ClientService service;

	@Autowired
	public ClientController(ClientService clientService) {
		this.service = clientService;
	}
	
	
	@GetMapping
	@ApiOperation(value = "Rota que busca todos os usuários")
	public ResponseEntity<Response<Page<ClientDTO>>> findAll(@PageableDefault(page = 0, size = 10, sort = {"id"}) Pageable pageable) throws ClientNotFoundException{
		Response<Page<ClientDTO>> response = new Response<>();
		
		Page<Client> findClients = service.findByExcludedFalsePageable(pageable);
		
		if(findClients.isEmpty()) {
			throw new ClientNotFoundException("Nenhum cliente encontrado!");
		}
		
		Page<ClientDTO> users = findClients.map(u -> u.convertEntityToDTO());
		
		response.setData(users);
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}


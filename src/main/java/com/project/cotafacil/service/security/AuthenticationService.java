package com.project.cotafacil.service.security;

import com.project.cotafacil.dto.model.security.JwtUserDTO;
import com.project.cotafacil.dto.model.security.TokenDTO;
import com.project.cotafacil.model.dto.response.Response;

public interface AuthenticationService {
	
	TokenDTO authenticate(JwtUserDTO dto); 
	
}

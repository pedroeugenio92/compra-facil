package com.project.cotafacil.controller.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cotafacil.dto.model.security.JwtUserDTO;
import com.project.cotafacil.dto.model.security.TokenDTO;
import com.project.cotafacil.model.dto.response.Response;
import com.project.cotafacil.util.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping
	public ResponseEntity<Response<TokenDTO>> generateTokenJwt(@Valid @RequestBody JwtUserDTO dto, BindingResult result) throws AuthenticationException {
		
		Response<TokenDTO> response = new Response<>();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("crip 123456 "+encoder.encode("123456"));
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addErrorMsgToResponse(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Authentication authentication = authenticationManager.authenticate
				(new UsernamePasswordAuthenticationToken(dto.getMail(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getMail());
		String token = jwtTokenUtil.getToken(userDetails);
		response.setData(new TokenDTO(token));
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}

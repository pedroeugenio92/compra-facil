package com.project.cotafacil.model.dto.user;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.project.cotafacil.model.user.User;
import com.project.cotafacil.util.security.BcryptUtil;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	@Getter
	private Long id;
	
	@Getter
	@NotNull(message = "O nome não pode ser nulo.")
	@Length(min=3, max=255, message="O nome deve conter entre 3 e 255 caracteres.")
	private String name;
	
	@NotNull(message = "A senha não pode ser nula.")
	@Length(min=6, message="A senha deve conter pelo menos 6 caracteres.")
	private String password;
	
	@Getter
	@Length(max=255, message="O email deve conter no máximo 255 caracteres.")
	@Email(message="Email inválido.")
	private String mail;

	
	public String getPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return BcryptUtil.encode(password);
	}
	
	public User convertDTOToEntity() {
		return new ModelMapper().map(this, User.class);
	}
}







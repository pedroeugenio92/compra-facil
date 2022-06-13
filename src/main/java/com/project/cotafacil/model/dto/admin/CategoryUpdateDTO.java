package com.project.cotafacil.model.dto.admin;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.cotafacil.model.admin.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryUpdateDTO {
	
	private int id;
	
	@NotNull(message = "O descrisção não pode ser nulo.")
	@Length(min=3, max=255, message="O descrisçãodeve conter entre 3 e 255 caracteres.")
	private String descriscao;
	
	@NotNull(message = "O solicitação não pode ser nulo.")
	@Length(max=255, message="O solicitação deve conter no máximo 255 caracteres.")
	private String solicitacao;
	
	public Category convertDTOToEntity() {
		return new ModelMapper().map(this, Category.class);
	}
	
}
package com.project.cotafacil.model.admin;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;


import com.project.cotafacil.model.address.Address;
import com.project.cotafacil.model.client.Client;
import com.project.cotafacil.model.dto.admin.CategoryDTO;
import com.project.cotafacil.model.dto.provider.ProviderDTO;

import com.project.cotafacil.model.provider.Provider;
import com.project.cotafacil.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categoria")
public class Category implements Serializable {
	
	private static final long serialVersionUID = -8553864086130686408L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "descricao")
	private String description;
	
	@Column(name = "solicitacao")
	private boolean request;
	
	@Column(name = "excluido")
	private boolean excluded;

	@Column(name = "data_criacao")
	private LocalDateTime creationDate;
		
	@PrePersist
    public void prePersist() {
		creationDate = LocalDateTime.now();
		excluded = false;
    }
	
	public CategoryDTO convertEntityToDTO() {
		return new ModelMapper().map(this, CategoryDTO.class);
	}
	
}

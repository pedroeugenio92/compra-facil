package com.project.cotafacil.model.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.project.cotafacil.enumeration.RoleEnum;
import com.project.cotafacil.model.client.Client;
import com.project.cotafacil.model.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class User implements Serializable {
	
	private static final long serialVersionUID = -8553864086130686408L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome")
	private String name;

	@Column(name = "email")
	private String mail;
	
	@Column(name = "senha")
	private String password;
	

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "telefone")
	private String phone;

	@Column(name = "ativo")
	private boolean actived;
	
	@Column(name = "excluido")
	private boolean excluded;

	@Column(name = "data_criacao")
	private LocalDateTime creationDate;
		
	@ManyToOne
    @JoinColumn(name="cliente_id", nullable=true)
	private Client client;
	
	@PrePersist
    public void prePersist() {
		creationDate = LocalDateTime.now();
		actived = true;
		excluded = false;
    }
	
	public UserDTO convertEntityToDTO() {
		return new ModelMapper().map(this, UserDTO.class);
	}
	
}

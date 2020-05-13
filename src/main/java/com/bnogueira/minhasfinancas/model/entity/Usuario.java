package com.bnogueira.minhasfinancas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // faz que o JPA reconheça com mapeamento de uma entidade
@Table(name = "usuario", schema = "financas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Setter
	@Column(name = "nome")
	private String nome;

	@Setter
	@Column(name = "email")
	private String email;
	

	@Column(name = "senha")
	private String senha;
	

	


	
	

}

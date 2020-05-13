package com.bnogueira.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bnogueira.minhasfinancas.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager; // entityManager, operações no banco de dados

	@Test
	public void deveVerificarAExistenciaDeEmail() {

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		boolean result = repository.existsByEmail("usuario@email.com");

		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {

		boolean result = repository.existsByEmail("usuario@email.com");

		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		Usuario usuario = criarUsuario();

		Usuario usuarioSalvo = repository.save(usuario);

		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();

	}

	@Test

	public void deveBuscarUmUsuarioPorEmail() {

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();

	}
	
	
	@Test

	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {

		
		
		
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();

	}

	public static Usuario criarUsuario() {

		return Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();

	}

}

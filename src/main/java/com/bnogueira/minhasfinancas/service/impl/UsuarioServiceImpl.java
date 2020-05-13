package com.bnogueira.minhasfinancas.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bnogueira.minhasfinancas.exception.ErroAutenticacao;
import com.bnogueira.minhasfinancas.exception.RegraNegocioException;
import com.bnogueira.minhasfinancas.model.entity.Usuario;
import com.bnogueira.minhasfinancas.model.repository.UsuarioRepository;
import com.bnogueira.minhasfinancas.service.UsuarioService;

@Service 
public class UsuarioServiceImpl  implements UsuarioService {

	
	private UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			
			throw new ErroAutenticacao("Usuário não encontrado");
			
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			
			throw new ErroAutenticacao("Senha inválida");
			
		}
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			
			throw new RegraNegocioException("Já existe um usuário cadstrado com esse email");
			
		}
		
	}

}

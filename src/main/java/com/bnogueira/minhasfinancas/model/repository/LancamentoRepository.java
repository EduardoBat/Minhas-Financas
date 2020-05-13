package com.bnogueira.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bnogueira.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

	
	
}

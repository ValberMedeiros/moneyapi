package com.algaworks.moneyapi.api.repository;

import com.algaworks.moneyapi.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}

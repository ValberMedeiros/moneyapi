package com.algaworks.moneyapi.api.repository;

import com.algaworks.moneyapi.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCodigo(Long codigo);
}

package com.algaworks.moneyapi.api.repository;

import com.algaworks.moneyapi.api.model.Categoria;
import com.algaworks.moneyapi.api.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

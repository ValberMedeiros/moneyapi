package com.algaworks.moneyapi.api.repository;

import com.algaworks.moneyapi.api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

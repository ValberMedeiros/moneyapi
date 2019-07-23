package com.algaworks.moneyapi.api.repository.lancamento;

import java.util.List;

import com.algaworks.moneyapi.api.model.Lancamento;
import com.algaworks.moneyapi.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}

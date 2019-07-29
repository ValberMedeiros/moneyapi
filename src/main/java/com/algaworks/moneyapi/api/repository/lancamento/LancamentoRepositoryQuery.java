package com.algaworks.moneyapi.api.repository.lancamento;

import com.algaworks.moneyapi.api.model.Lancamento;
import com.algaworks.moneyapi.api.repository.filter.LancamentoFilter;
import com.algaworks.moneyapi.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}

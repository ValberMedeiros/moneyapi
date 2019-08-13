package com.algaworks.moneyapi.api.repository.pessoa;
import com.algaworks.moneyapi.api.model.Pessoa;
import com.algaworks.moneyapi.api.repository.filter.LancamentoFilter;
import com.algaworks.moneyapi.api.repository.filter.PessoaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepositoryQuery {

    Page<Pessoa> filtrar(PessoaFilter pessoaFilter, Pageable pageable);

}

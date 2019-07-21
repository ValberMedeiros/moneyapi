package com.algaworks.moneyapi.api.service;

import com.algaworks.moneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.moneyapi.api.model.Pessoa;
import com.algaworks.moneyapi.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pr;

    public Pessoa atualizar(Long codigo, Pessoa pessoa){
        Pessoa pessoaSalva = buscaPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

        return pr.save(pessoaSalva);
    }

    public void atualizaPropriedadeAtivo(Long codigo, Boolean ativo){
        Pessoa pessoaSalva = buscaPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pr.save(pessoaSalva);
    }

    private Pessoa buscaPessoaPeloCodigo(Long codigo) {
        return this.pr.findById(codigo)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }
}

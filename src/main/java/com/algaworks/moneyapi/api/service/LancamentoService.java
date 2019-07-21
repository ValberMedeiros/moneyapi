package com.algaworks.moneyapi.api.service;

import com.algaworks.moneyapi.api.model.Lancamento;
import com.algaworks.moneyapi.api.model.Pessoa;
import com.algaworks.moneyapi.api.repository.LancamentoRepository;
import com.algaworks.moneyapi.api.repository.PessoaRepository;
import com.algaworks.moneyapi.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pr;

    @Autowired
    private LancamentoRepository lr;

    public Lancamento save (Lancamento lancamento){
        Pessoa pessoa = pr.findByCodigo(lancamento.getPessoa().getId());
        if(pessoa == null || pessoa.isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
        return lr.save(lancamento);
    }
}

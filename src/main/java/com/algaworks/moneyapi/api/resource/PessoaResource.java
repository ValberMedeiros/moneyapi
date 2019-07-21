package com.algaworks.moneyapi.api.resource;

import com.algaworks.moneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.moneyapi.api.model.Pessoa;
import com.algaworks.moneyapi.api.repository.PessoaRepository;
import com.algaworks.moneyapi.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pr;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService ps;

    @GetMapping
    public List<Pessoa> listar(){
        List<Pessoa> pessoas = (List<Pessoa>) pr.findAll();
        return pessoas;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pr.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscarPessoaPeloId(@PathVariable Long codigo){
        try{
            Optional<Pessoa> pessoaBuscada = pr.findById(codigo);
            return ResponseEntity.ok(pessoaBuscada.get());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public  ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = ps.atualizar(codigo, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    @PutMapping("/{codigo}/ativo")
    public void atualizarProriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
        ps.atualizaPropriedadeAtivo(codigo, ativo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){
        pr.deleteById(codigo);
    }
}

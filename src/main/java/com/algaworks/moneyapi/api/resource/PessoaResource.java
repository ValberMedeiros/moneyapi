package com.algaworks.moneyapi.api.resource;

import com.algaworks.moneyapi.api.model.Pessoa;
import com.algaworks.moneyapi.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    PessoaRepository pr;

    @GetMapping
    public List<Pessoa> listar(){
        List<Pessoa> pessoas = (List<Pessoa>) pr.findAll();
        return pessoas;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pr.save(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(pessoaSalva.getId()).toUri();
        response.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoaSalva);
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
}

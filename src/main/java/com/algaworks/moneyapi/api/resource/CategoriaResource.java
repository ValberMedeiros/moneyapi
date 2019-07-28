package com.algaworks.moneyapi.api.resource;

import com.algaworks.moneyapi.api.event.RecursoCriadoEvent;
import com.algaworks.moneyapi.api.model.Categoria;
import com.algaworks.moneyapi.api.repository.CategoriaRepository;
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
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository cr;

    @Autowired
    private ApplicationEventPublisher publisher;

    @CrossOrigin
    @GetMapping
    public List<Categoria> listar(){
        List<Categoria> categorias = cr.findAll();
        return categorias;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
        Categoria categoriaSalva = cr.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long id){
        try{
            Optional<Categoria> categoriaBuscada = cr.findById(id);
            return ResponseEntity.ok(categoriaBuscada.get());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}

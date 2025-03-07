package com.github.dangelcrack.diccionariospring.controllers;

import com.github.dangelcrack.diccionariospring.exceptions.RecordNotFoundException;
import com.github.dangelcrack.diccionariospring.models.Definicion;
import com.github.dangelcrack.diccionariospring.services.DefinicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/definiciones")
public class DefinicionController {

    @Autowired
    private DefinicionService definicionService;

    @GetMapping
    public ResponseEntity<List<Definicion>> findAll() {
        List<Definicion> list = definicionService.getAllDefiniciones();
        return ResponseEntity.ok(list);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Definicion> findDefinicionById(@PathVariable Long id) throws RecordNotFoundException {
        Definicion definicion = definicionService.getDefinicionById(id);
        return ResponseEntity.ok(definicion);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Definicion> createDefinicion(@RequestBody Definicion definicion) {
        Definicion createdDefinicion = definicionService.createDefinicion(definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDefinicion);
    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Definicion> updateDefinicion(@PathVariable Long id, @RequestBody Definicion updatedDefinicion) throws RecordNotFoundException {
        Definicion definicionUpdated = definicionService.updateDefinicion(id, updatedDefinicion);
        return ResponseEntity.ok(definicionUpdated);
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDefinicion(@PathVariable Long id) throws RecordNotFoundException {
        definicionService.deleteDefinicion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.github.dangelcrack.diccionariospring.controllers;

import com.github.dangelcrack.diccionariospring.exceptions.RecordNotFoundException;
import com.github.dangelcrack.diccionariospring.models.Definicion;
import com.github.dangelcrack.diccionariospring.models.Palabra;
import com.github.dangelcrack.diccionariospring.services.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/palabras")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @GetMapping
    public ResponseEntity<List<Palabra>> getAllPalabras() {
        List<Palabra> list = palabraService.getAllPalabras();
        return ResponseEntity.ok(list);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Palabra> getPalabraById(@PathVariable Long id) throws RecordNotFoundException {
        Palabra palabra = palabraService.getPalabraById(id);
        return ResponseEntity.ok(palabra);
    }

    @CrossOrigin
    @PostMapping("/palabras")
    public ResponseEntity<Palabra> createPalabra(@RequestBody Palabra palabra) {
        Palabra createdPalabra = palabraService.createPalabra(palabra);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPalabra);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Palabra> updatePalabra(@PathVariable Long id, @RequestBody Palabra updatedPalabra) throws RecordNotFoundException {
        Palabra palabraUpdated = palabraService.updatePalabra(id, updatedPalabra);
        return ResponseEntity.ok(palabraUpdated);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public HttpStatus deletePalabra(@PathVariable Long id) throws RecordNotFoundException {
        palabraService.deletePalabra(id);
        return HttpStatus.ACCEPTED;
    }

    @CrossOrigin
    @GetMapping("/search/{categoriaGramatical}")
    public ResponseEntity<List<Palabra>> searchPalabras(@PathVariable String categoriaGramatical) {
        List<Palabra> palabrasList = palabraService.getByCategoriaGramatical(categoriaGramatical);
        return ResponseEntity.ok(palabrasList);
    }

    @CrossOrigin
    @GetMapping("/{id}/definiciones")
    public ResponseEntity<List<Definicion>> getDefinicionesByPalabraId(@PathVariable Long id) throws RecordNotFoundException {
        List<Definicion> definiciones = palabraService.getDefinicionesByPalabraId(id);
        return ResponseEntity.ok(definiciones);
    }

    @CrossOrigin
    @PostMapping("/{id}/definiciones")
    public ResponseEntity<Definicion> addDefinicionToPalabra(@PathVariable Long id, @RequestBody Definicion definicion) throws RecordNotFoundException {
        Definicion nuevaDefinicion = palabraService.addDefinicionToPalabra(id, definicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDefinicion);
    }
    @CrossOrigin
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Palabra>> getPalabrasByCategoria(@PathVariable String categoria) {
        List<Palabra> palabras = palabraService.findByCategoriaGramatical(categoria);
        return ResponseEntity.ok(palabras);
    }
    @GetMapping("/inicial/{letra}")
    public ResponseEntity<List<Palabra>> getPalabrasByInicial(@PathVariable String letra) {
        List<Palabra> palabras = palabraService.findByInicial(letra);
        return ResponseEntity.ok(palabras);
    }

}

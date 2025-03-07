package com.github.dangelcrack.diccionariospring.services;

import com.github.dangelcrack.diccionariospring.exceptions.RecordNotFoundException;
import com.github.dangelcrack.diccionariospring.models.Definicion;
import com.github.dangelcrack.diccionariospring.repositories.DefinicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefinicionService {

    @Autowired
    private DefinicionRepository repository;

    public List<Definicion> getAllDefiniciones() {
        return repository.findAll();
    }

    public Definicion getDefinicionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe definición para el id: " + id,id));
    }

    public Definicion createDefinicion(Definicion definicion) {
        return repository.save(definicion);
    }

    public Definicion updateDefinicion(Long id, Definicion updatedDefinicion) throws RecordNotFoundException {
        Definicion existingDefinicion = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe definición para el id: " + id,id));

        existingDefinicion.setDescripcion(updatedDefinicion.getDescripcion());
        existingDefinicion.setEjemplo(updatedDefinicion.getEjemplo());
        existingDefinicion.setPalabra(updatedDefinicion.getPalabra());

        return repository.save(existingDefinicion);
    }

    public void deleteDefinicion(Long id) throws RecordNotFoundException {
        Definicion definicion = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe definición para el id: " + id,id));
        repository.delete(definicion);
    }
}

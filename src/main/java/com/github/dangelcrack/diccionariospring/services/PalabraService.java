package com.github.dangelcrack.diccionariospring.services;

import com.github.dangelcrack.diccionariospring.exceptions.RecordNotFoundException;
import com.github.dangelcrack.diccionariospring.models.Definicion;
import com.github.dangelcrack.diccionariospring.models.Palabra;
import com.github.dangelcrack.diccionariospring.repositories.DefinicionRepository;
import com.github.dangelcrack.diccionariospring.repositories.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraService {

    @Autowired
    private PalabraRepository repository;
    @Autowired
    private DefinicionRepository definicionRepository;

    public List<Palabra> getAllPalabras() {
        List<Palabra> palabras = repository.findAll();
        palabras.forEach(palabra -> palabra.setDefiniciones(null));
        return palabras;
    }

    public Palabra getPalabraById(Long id) throws RecordNotFoundException {
        Palabra palabra = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe palabra para el id: " + id, id));
        palabra.setDefiniciones(null);
        return palabra;
    }

    public Palabra createPalabra(Palabra palabra) {
        return repository.save(palabra);
    }

    public Palabra updatePalabra(Long id, Palabra palabraActualizada) throws RecordNotFoundException {
        Palabra palabraExistente = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Palabra no encontrada con el id ", id));
        palabraExistente.setTermino(palabraActualizada.getTermino());
        palabraExistente.setCategoriaGramatical(palabraActualizada.getCategoriaGramatical());
        if (palabraActualizada.getDefiniciones() != null) {
            palabraExistente.getDefiniciones().clear();
            for (Definicion definicion : palabraActualizada.getDefiniciones()) {
                definicion.setPalabra(palabraExistente);
                palabraExistente.getDefiniciones().add(definicion);
            }
        }
        return repository.save(palabraExistente);
    }

    public void deletePalabra(Long id) throws RecordNotFoundException {
        Palabra palabra = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No existe palabra para el id: " + id,id));
        repository.delete(palabra);
    }

    public List<Palabra> getByCategoriaGramatical(String categoriaGramatical) {
        return repository.getbyCategoriaGramatical(categoriaGramatical);
    }
    public List<Definicion> getDefinicionesByPalabraId(Long id) throws RecordNotFoundException {
        Palabra palabra = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Palabra no encontrada",id));
        List<Definicion> definiciones = definicionRepository.findByPalabraId(id);
        if (definiciones.isEmpty()) {
            throw new RecordNotFoundException("No se encontraron definiciones para esta palabra",id);
        }

        return definiciones;
    }
    public Definicion addDefinicionToPalabra(Long id, Definicion definicion) throws RecordNotFoundException {
        Palabra palabra = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Palabra no encontrada con el id: " + id,id));
        definicion.setPalabra(palabra);
        return definicionRepository.save(definicion);
    }
    public List<Palabra> findByCategoriaGramatical(String categoria) {
        return repository.findByCategoriaGramatical(categoria);
    }
    public List<Palabra> findByInicial(String letra) {
        return repository.findByterminoStartingWithIgnoreCase(letra);
    }
}


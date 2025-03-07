package com.github.dangelcrack.diccionariospring.repositories;

import com.github.dangelcrack.diccionariospring.models.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PalabraRepository extends JpaRepository<Palabra, Long> {

    @Query(
            value="SELECT * FROM palabra AS p WHERE p.categoria_gramatical LIKE %?1",
            nativeQuery=true
    )
    public List<Palabra> getbyCategoriaGramatical(String categoria_Gramatical);
    List<Palabra> findByCategoriaGramatical(String categoria);
    List<Palabra> findByterminoStartingWithIgnoreCase(String termino);
}

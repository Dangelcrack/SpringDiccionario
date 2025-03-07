package com.github.dangelcrack.diccionariospring.repositories;

import com.github.dangelcrack.diccionariospring.models.Definicion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DefinicionRepository extends JpaRepository<Definicion, Long> {
    List<Definicion> findByPalabraId(Long palabraId);
}

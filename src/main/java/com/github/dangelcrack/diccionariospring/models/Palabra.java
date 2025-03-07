package com.github.dangelcrack.diccionariospring.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "palabra")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Palabra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String termino;
    private String categoriaGramatical;

    @OneToMany(mappedBy = "palabra", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Definicion> definiciones;

    public Palabra() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermino() {
        return termino;
    }

    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getCategoriaGramatical() {
        return categoriaGramatical;
    }

    public void setCategoriaGramatical(String categoriaGramatical) {
        this.categoriaGramatical = categoriaGramatical;
    }

    public List<Definicion> getDefiniciones() {
        return definiciones;
    }

    public void setDefiniciones(List<Definicion> definiciones) {
        this.definiciones = definiciones;
    }

    @Override
    public String toString() {
        return "Palabra{" +
                "id=" + id +
                ", termino='" + termino + '\'' +
                ", categoriaGramatical='" + categoriaGramatical + '\'' +
                '}';
    }

}

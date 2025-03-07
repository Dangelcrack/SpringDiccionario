package com.github.dangelcrack.diccionariospring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "definicion")
public class Definicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String ejemplo;

    @ManyToOne
    @JoinColumn(name = "palabra_id")
    @JsonBackReference
    private Palabra palabra;

    public Definicion() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        return "Definicion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", ejemplo='" + ejemplo + '\'' +
                '}';
    }
}

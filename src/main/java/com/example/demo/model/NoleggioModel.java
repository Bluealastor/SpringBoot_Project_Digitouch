package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "noleggio")
@NoArgsConstructor
@AllArgsConstructor
public class NoleggioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String titolare;

    @NotNull
    private String citta;

    @ManyToMany(mappedBy = "noleggi")
    private Set<FilmModel> film = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getTitolare() {
        return titolare;
    }

    public void setTitolare(@NotNull String titolare) {
        this.titolare = titolare;
    }

    public @NotNull String getCitta() {
        return citta;
    }

    public void setCitta(@NotNull String citta) {
        this.citta = citta;
    }

    public Set<FilmModel> getFilm() {
        return film;
    }

    public void setFilm(Set<FilmModel> film) {
        this.film = film;
    }
}

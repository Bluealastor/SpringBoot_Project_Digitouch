package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
public class FilmModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String casaProduzione;

    @NotNull
    private String genere;

    private String annouscita;

    @NotNull
    private String hashFilm;

    @ManyToMany
    @JoinTable(
            name = "film_noleggio",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "noleggio_id")
    )
    private Set<NoleggioModel> noleggi = new HashSet<>();


    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public @NotNull String getCasaProduzione() {
        return casaProduzione;
    }

    public void setCasaProduzione(@NotNull String casaProduzione) {
        this.casaProduzione = casaProduzione;
    }

    public @NotNull String getGenere() {
        return genere;
    }

    public void setGenere(@NotNull String genere) {
        this.genere = genere;
    }

    public String getAnnouscita() {
        return annouscita;
    }

    public void setAnnouscita(String annouscita) {
        this.annouscita = annouscita;
    }

    public @NotNull String getHashFilm() {
        return hashFilm;
    }

    public void setHashFilm(@NotNull String hashFilm) {
        this.hashFilm = hashFilm;
    }

    public Set<NoleggioModel> getNoleggi() {
        return noleggi;
    }

    public void setNoleggi(Set<NoleggioModel> noleggi) {
        this.noleggi = noleggi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
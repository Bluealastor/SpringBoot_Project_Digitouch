package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tt_film")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmModel{

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

    public FilmModel(Long id) {
        this.id = id;
    }

}
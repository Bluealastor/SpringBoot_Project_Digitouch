package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;



@Entity
@Table(name = "tt_film")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String casaProduzione;

    @NotBlank
    private String genere;

    private String annouscita;

    @NotBlank
    private String hashFilm;

    public FilmModel(Long id) {
        this.id = id;
    }

}
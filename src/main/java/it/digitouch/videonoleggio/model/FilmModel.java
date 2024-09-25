package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "tt_film")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String casaProduzione;

    @NotBlank
    @Column(nullable = false)
    private String genere;

    private String annouscita;

    @NotBlank
    @Column(unique = true,nullable = false)
    private String hashFilm;

    @OneToMany(mappedBy = "film")
    private List<FilmNoleggioModel> filmNoleggioModelList;

    public FilmModel(Long id) {
        this.id = id;
    }

}
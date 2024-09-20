package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tc_film_noleggio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmNoleggioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmModel film;

    @ManyToOne
    @JoinColumn(name = "noleggio_id", nullable = false)
    private NoleggioModel noleggio;
}

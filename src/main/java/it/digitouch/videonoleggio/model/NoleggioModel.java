package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "tt_noleggio")
@Data
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

    @NotNull
    private String hashNoleggio;

    public NoleggioModel(Long id) {
        this.id = id;
    }
}

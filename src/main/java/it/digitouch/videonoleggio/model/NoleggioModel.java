package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tt_noleggio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoleggioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     /*******************************************************************************************
     * UTILIZZO PER LE STRINGHE NOTBLANK in modo da evitare spazi vuoti all'inizio o alla fine  *
     *******************************************************************************************/
    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String titolare;

    @NotBlank
    @Column(nullable = false)
    private String citta;

    @NotBlank
    @Column(unique = true,nullable = false)
    private String hashNoleggio;

    @OneToMany(mappedBy = "noleggio")
    private List<FilmNoleggioModel> filmNoleggioModelList;

    public NoleggioModel(Long id) {
        this.id = id;
    }
}

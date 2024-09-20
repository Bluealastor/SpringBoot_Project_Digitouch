package it.digitouch.videonoleggio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

     /*******************************************************************************************
     * UTILIZZO PER LE STRINGHE NOTBLANK in modo da evitare spazi vuoti all'inizio o alla fine  *
     *******************************************************************************************/
    @NotBlank
    private String nome;

    @NotBlank
    private String titolare;

    @NotBlank
    private String citta;

    @NotBlank
    private String hashNoleggio;

    public NoleggioModel(Long id) {
        this.id = id;
    }
}

package it.digitouch.videonoleggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoleggioDTO {

    private Long id;
    private String nome;
    private String titolare;
    private String citta;
    private String hashNoleggio;
}

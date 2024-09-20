package it.digitouch.videonoleggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoleggioDTO {

    private Long id;
    private String nome;
    private String titolare;
    private String citta;
    private String hashNoleggio;
}

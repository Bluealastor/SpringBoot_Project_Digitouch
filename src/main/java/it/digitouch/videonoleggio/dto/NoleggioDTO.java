package it.digitouch.videonoleggio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoleggioDTO {

    private Long id;
    private String nome;
    private String titolare;
    private String citta;
    private String hashNoleggio;

    public String getHashNoleggio() {
        String encode64 = nome + titolare + citta;
        return Base64.getUrlEncoder().encodeToString(encode64.getBytes());
    }
}

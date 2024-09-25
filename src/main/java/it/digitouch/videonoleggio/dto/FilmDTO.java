package it.digitouch.videonoleggio.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDTO {
    private Long id;
    private String nome;
    private String casaProduzione;
    private String genere;
    private String annouscita;
    private String hashFilm;

    public String getHashFilm() {
        String encode64 = nome + casaProduzione + genere + annouscita;
        return Base64.getUrlEncoder().encodeToString(encode64.getBytes());
    }
}

package it.digitouch.videonoleggio.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private Long id;
    private String nome;
    private String casaProduzione;
    private String genere;
    private String annouscita;
    private String hashFilm;
}

package it.digitouch.videonoleggio.dto;


import lombok.*;

@Data
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    private String nome;
    private String casaProduzione;
    private String genere;
    private String annouscita;
    private String hashFilm;
}

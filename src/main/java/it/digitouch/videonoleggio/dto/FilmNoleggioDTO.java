package it.digitouch.videonoleggio.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmNoleggioDTO {
    private Long id;
    private Long filmId;
    private Long noleggioId;

    public FilmNoleggioDTO(Long filmId) {
        this.filmId = filmId;
    }

}

package it.digitouch.videonoleggio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmNoleggioDTO {
    private Long id;
    private Long filmId;
    private Long noleggioId;

    public FilmNoleggioDTO(Long filmId) {
        this.filmId = filmId;
    }

}

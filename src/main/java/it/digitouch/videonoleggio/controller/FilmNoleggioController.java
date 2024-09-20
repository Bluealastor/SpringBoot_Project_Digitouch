package it.digitouch.videonoleggio.controller;

import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.model.NoleggioModel;
import it.digitouch.videonoleggio.model.FilmNoleggioModel;
import it.digitouch.videonoleggio.repository.FilmRepository;
import it.digitouch.videonoleggio.repository.NoleggioRepository;
import it.digitouch.videonoleggio.repository.FilmNoleggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/filmNoleggio")
@RequiredArgsConstructor
public class FilmNoleggioController {

    private final FilmRepository filmRepository;

    private final NoleggioRepository noleggioRepository;

    private final FilmNoleggioRepository filmNoleggioRepository;

    @PostMapping("/collega")
    public ResponseEntity<String> collegaFilmANoleggio(@RequestBody Map<String, Long> request) {
        Long filmId = request.get("filmId");
        Long noleggioId = request.get("noleggioId");

        if (filmId == null || noleggioId == null) {
            return ResponseEntity.badRequest().body("filmId e noleggioId sono richiesti");
        }

        FilmModel film = filmRepository.findById(filmId).orElseThrow(()-> new ElementNotFoundException("Film non trovato"));
        NoleggioModel noleggio = noleggioRepository.findById(noleggioId).orElseThrow(()-> new ElementNotFoundException("Noleggio non trovato"));

        FilmNoleggioModel filmNoleggio = new FilmNoleggioModel();
        filmNoleggio.setFilm(film);
        filmNoleggio.setNoleggio(noleggio);

        filmNoleggioRepository.save(filmNoleggio);

        return ResponseEntity.ok("Film collegato al noleggio con successo");
    }
}

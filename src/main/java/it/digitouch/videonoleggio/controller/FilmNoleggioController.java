package it.digitouch.videonoleggio.controller;

import it.digitouch.videonoleggio.dto.FilmNoleggioDTO;
import it.digitouch.videonoleggio.service.FilmNoleggioService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/filmNoleggio")
@RequiredArgsConstructor
public class FilmNoleggioController {

private final FilmNoleggioService filmNoleggioService;

    @PostMapping("/collega")
    public ResponseEntity<FilmNoleggioDTO> collegaFilmNoleggio(@Valid @RequestBody FilmNoleggioDTO request) {
        FilmNoleggioDTO savedNoleggioFilm = filmNoleggioService.saveFilmNoleggio(request);
        return ResponseEntity.ok(savedNoleggioFilm);
    }
}

package it.digitouch.videonoleggio.controller;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.service.FilmService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/film")
@RequiredArgsConstructor //add all DI for constructor
public class FilmController {
   //TODO in maiuscolo crea e segna cose da fare
   private final FilmService filmService;

   @GetMapping
   public ResponseEntity<List<FilmDTO>> getAllFilms() {
       List<FilmDTO> films = filmService.getAllFilms();
       return ResponseEntity.ok(films);
   }

   @PostMapping
   public ResponseEntity<FilmDTO> createFilm(@Valid @RequestBody FilmDTO filmDTO) {
       FilmDTO savedFilm = filmService.saveFilm(filmDTO);
       return ResponseEntity.ok(savedFilm);
   }

        @GetMapping("/{id}")
        public ResponseEntity<FilmDTO> getFilmById(@PathVariable Long id) {
            FilmDTO filmDTO = filmService.getFilmById(id);
            return ResponseEntity.ok(filmDTO);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteFilm(@PathVariable Long id) {
       try {
           filmService.deleteFilmById(id);
           return ResponseEntity.ok("Film eliminato con id: " + id);
       }catch (EmptyResultDataAccessException e) {

           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film non trovato con id: " + id);
       }
        }

        @DeleteMapping("/delete/{hash}")
        public ResponseEntity<String> deleteFilmByHash(@PathVariable String hash){
            try {
                filmService.deleFilmByHash(hash);
                return ResponseEntity.ok("Film eliminato con hash: " + hash);
            } catch (EmptyResultDataAccessException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Film non trovato con hash: " + hash);
            }
        }

    @PatchMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Long id, @Valid @RequestBody FilmDTO filmDTO) {
        FilmDTO updatedFilm = filmService.updateFilm(id, filmDTO);
        return ResponseEntity.ok(updatedFilm);
    }
    }

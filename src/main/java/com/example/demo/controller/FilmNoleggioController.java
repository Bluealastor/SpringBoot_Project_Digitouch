package com.example.demo.controller;

import com.example.demo.service.FilmNoleggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insert")
public class FilmNoleggioController {

    @Autowired
    private FilmNoleggioService filmNoleggioService;

    @PostMapping()
    public ResponseEntity<String> collegaFilmANoleggio(@RequestParam Long filmId, @RequestParam Long noleggioId) {
        try {
            filmNoleggioService.collegaFilmANoleggio(filmId, noleggioId);
            return ResponseEntity.ok("Film collegato al noleggio con successo");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore: " + e.getMessage());
        }
    }

}

package com.example.demo.service;

import com.example.demo.model.FilmModel;
import com.example.demo.model.NoleggioModel;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.NoleggioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmNoleggioService {
    @Autowired
    FilmRepository filmRepository;

    @Autowired
    NoleggioRepository noleggioRepository;

    @Transactional
    public void collegaFilmANoleggio(Long filmId, Long noleggioId) {
        FilmModel film = filmRepository.findById(filmId).orElseThrow(() -> new RuntimeException("Film non trovato"));
        NoleggioModel noleggio = noleggioRepository.findById(noleggioId).orElseThrow(() -> new RuntimeException("Noleggio non trovato"));

        // Aggiungi il noleggio alla collezione di noleggi del film
        film.getNoleggi().add(noleggio);

        // Aggiungi il film alla collezione di film del noleggio
        noleggio.getFilm().add(film);

        // Salva entrambe le entità
        filmRepository.save(film);
        noleggioRepository.save(noleggio);
    }
}

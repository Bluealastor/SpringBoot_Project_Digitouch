package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.repository.FilmRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;

@Service
@Slf4j @Data
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    private final ModelMapper modelMapper;

    public List<FilmDTO> getAllFilms() {
        List<FilmModel> filmList = filmRepository.findAll();
        return filmList.stream().map(((film) -> modelMapper.map(film, FilmDTO.class))).toList();
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        FilmModel film = modelMapper.map(filmDTO, FilmModel.class);
        filmRepository.save(film);
        return modelMapper.map(film, FilmDTO.class);
    }

    public FilmDTO getFilmById(Long id) {
        FilmModel filmModel = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trovato"));
        return modelMapper.map(filmModel, FilmDTO.class);
    }

    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) {    // Trova il film nel database
        FilmModel filmModel = filmRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Film non trovato"));
        filmModel.setNome(filmDTO.getNome());
        filmModel.setCasaProduzione(filmDTO.getCasaProduzione());
        filmModel.setGenere(filmDTO.getGenere());
        filmModel.setAnnouscita(filmDTO.getAnnouscita());
        filmModel.setHashFilm(filmDTO.getHashFilm());

        // Salva il film aggiornato nel repository
        filmRepository.save(filmModel);

        // Ritorna il film aggiornato come DTO
        return modelMapper.map(filmModel, FilmDTO.class);
    }
}
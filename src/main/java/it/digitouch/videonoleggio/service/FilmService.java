package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.exception.ElementAlreadyFoundException;
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
        return filmList.stream().map(film -> modelMapper.map(film, FilmDTO.class)).toList();
    }

    public FilmDTO saveFilm(FilmDTO filmDTO) {
        filmRepository.findByhashFilm(filmDTO.getHashFilm())
                .orElseThrow(() -> new ElementNotFoundException("Film "+ filmDTO.getHashFilm() + " Esiste già"));
        FilmModel film = modelMapper.map(filmDTO, FilmModel.class);
        filmRepository.save(film);
        return modelMapper.map(film, FilmDTO.class);
    }

    public FilmDTO getFilmById(Long id) {
        FilmModel filmModel = filmRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Film "+ id + " Not Found"));
        return modelMapper.map(filmModel, FilmDTO.class);
    }

    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) {
        FilmModel filmModel = filmRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Film "+ id + " Not Found"));
        filmModel.setNome(filmDTO.getNome());
        filmModel.setCasaProduzione(filmDTO.getCasaProduzione());
        filmModel.setGenere(filmDTO.getGenere());
        filmModel.setAnnouscita(filmDTO.getAnnouscita());
        filmModel.setHashFilm(filmDTO.getHashFilm());

        filmRepository.findByhashFilm(filmDTO.getHashFilm())
                .orElseThrow(() -> new ElementAlreadyFoundException("Un film con codice " + filmDTO.getHashFilm() + " esiste già."));
        filmRepository.save(filmModel);

        return modelMapper.map(filmModel, FilmDTO.class);
    }
}
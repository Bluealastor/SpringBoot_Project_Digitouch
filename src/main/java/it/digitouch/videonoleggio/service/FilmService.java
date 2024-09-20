package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.repository.FilmRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class FilmService {

    private final FilmRepository filmRepository;

    public List<FilmDTO> getAllFilms() {
        List<FilmModel> filmModels = filmRepository.findAll();
        List<FilmDTO> filmDTOs = new ArrayList<>();

        // Itera su ogni film e converto in DTO
        for (FilmModel filmModel : filmModels) {
            FilmDTO filmDTO = new FilmDTO(
                    filmModel.getId(),
                    filmModel.getNome(),
                    filmModel.getCasaProduzione(),
                    filmModel.getGenere(),
                    filmModel.getAnnouscita(),
                    filmModel.getHashFilm()
            );
            filmDTOs.add(filmDTO);
        }

        return filmDTOs;
    }


    public FilmDTO saveFilm(FilmDTO filmDTO) {
        FilmModel filmModel = new FilmModel(
                filmDTO.getId(),
                filmDTO.getNome(),
                filmDTO.getCasaProduzione(),
                filmDTO.getGenere(),
                filmDTO.getAnnouscita(),
                filmDTO.getHashFilm()
        );
        FilmModel savedFilm = filmRepository.save(filmModel);
        return new FilmDTO(
                savedFilm.getId(),
                savedFilm.getNome(),
                savedFilm.getCasaProduzione(),
                savedFilm.getGenere(),
                savedFilm.getAnnouscita(),
                savedFilm.getHashFilm()
        );
    }

    public FilmDTO getFilmById(Long id) {
        FilmModel filmModel = filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film non trovato"));
        return new FilmDTO(
                filmModel.getId(),
                filmModel.getNome(),
                filmModel.getCasaProduzione(),
                filmModel.getGenere(),
                filmModel.getAnnouscita(),
                filmModel.getHashFilm()
        );
    }

    public void deleteFilmById(Long id) {
        filmRepository.deleteById(id);
    }

    public FilmDTO updateFilm(Long id, FilmDTO filmDTO) {
        FilmModel existingFilm = filmRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Film non trovato"));
        existingFilm.setNome(filmDTO.getNome());
        existingFilm.setCasaProduzione(filmDTO.getCasaProduzione());
        existingFilm.setGenere(filmDTO.getGenere());
        existingFilm.setAnnouscita(filmDTO.getAnnouscita());
        existingFilm.setHashFilm(filmDTO.getHashFilm());
        filmRepository.save(existingFilm);
        return convertToDTO(existingFilm);
    }




    private FilmDTO convertToDTO(FilmModel filmModel) {
        return new FilmDTO(
                filmModel.getId(),
                filmModel.getNome(),
                filmModel.getCasaProduzione(),
                filmModel.getGenere(),
                filmModel.getAnnouscita(),
                filmModel.getHashFilm()
        );
    }
}
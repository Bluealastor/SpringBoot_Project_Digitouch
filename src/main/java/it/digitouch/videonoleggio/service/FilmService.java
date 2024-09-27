package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.exception.ElementAlreadyFoundException;
import it.digitouch.videonoleggio.exception.ElementNotFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.repository.FilmRepository;

import jakarta.transaction.Transactional;
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
                .ifPresent((film) -> {throw new ElementAlreadyFoundException("Film " + film.getHashFilm() + " esiste già");});
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
        filmRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Film con hash " + id + " non trovato"));
        filmRepository.deleteById(id);
    }


    /****************************************************************************************************************************************************************************
    * jakarta.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'remove' call           *
    * Questo errore si verifica perché l'operazione di eliminazione (delete) richiede una transazione attiva,                                                                   *
    * e l'EntityManager non è in un contesto transazionale.                                                                                                                     *
    * In Spring, le operazioni JPA come la delete devono essere eseguite all'interno di una transazione,                                                                        *
    *  e senza di essa si presenta questo problema.                                                                                                                             *
    ****************************************************************************************************************************************************************************/
    // aggiungere il transactional
    @Transactional
    public void deleFilmByHash(String hash){
        filmRepository.findByhashFilm(hash)
                .orElseThrow(() -> new ElementNotFoundException("Film con hash " + hash + " non trovato"));
        filmRepository.deleteByHashFilm(hash);
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
                .ifPresent(film -> { throw new ElementAlreadyFoundException("Film " + filmDTO.getHashFilm() + " esiste già"); });
        filmRepository.save(filmModel);

        return modelMapper.map(filmModel, FilmDTO.class);
    }
}
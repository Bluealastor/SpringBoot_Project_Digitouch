package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmNoleggioDTO;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.model.FilmNoleggioModel;
import it.digitouch.videonoleggio.model.NoleggioModel;
import it.digitouch.videonoleggio.repository.FilmNoleggioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j @Data
@RequiredArgsConstructor
public class FilmNoleggioService {

    private final FilmNoleggioRepository filmNoleggioRepository;

    private final ModelMapper modelMapper;

    public FilmNoleggioDTO saveFilmNoleggio(FilmNoleggioDTO filmNoleggioDTO) {
        FilmNoleggioModel filmNoleggio = new FilmNoleggioModel();
        filmNoleggio.setFilm(new FilmModel(filmNoleggioDTO.getFilmId()));
        filmNoleggio.setNoleggio(new NoleggioModel(filmNoleggioDTO.getNoleggioId()));
        filmNoleggioRepository.save(filmNoleggio);
        return modelMapper.map(filmNoleggio, FilmNoleggioDTO.class);
    }
}


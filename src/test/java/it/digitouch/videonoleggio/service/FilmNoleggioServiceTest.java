package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmNoleggioDTO;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.model.FilmNoleggioModel;
import it.digitouch.videonoleggio.model.NoleggioModel;
import it.digitouch.videonoleggio.repository.FilmNoleggioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmNoleggioServiceTest {

    @Mock
    private FilmNoleggioRepository filmNoleggioRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private FilmNoleggioService filmNoleggioService;

    @Test
    void saveFilmNoleggio_ok(){

        FilmNoleggioDTO filmNoleggioDTO = new FilmNoleggioDTO();
        filmNoleggioDTO.setFilmId(1L);
        filmNoleggioDTO.setNoleggioId(2L);

        FilmNoleggioModel filmNoleggioModel = new FilmNoleggioModel();
        filmNoleggioModel.setFilm(new FilmModel(1L));
        filmNoleggioModel.setNoleggio(new NoleggioModel(2L));

        when(filmNoleggioRepository.save(ArgumentMatchers.any())).thenReturn(filmNoleggioModel);
        when(modelMapper.map(ArgumentMatchers.any(), eq(FilmNoleggioDTO.class))).thenReturn(filmNoleggioDTO);

        var result = filmNoleggioService.saveFilmNoleggio(filmNoleggioDTO);

        assertNotNull(result);
        assertEquals(1L, result.getFilmId());
        assertEquals(2L, result.getNoleggioId());

        verify(filmNoleggioRepository, times(1)).save(any(FilmNoleggioModel.class));

        verify(modelMapper, times(1)).map(filmNoleggioModel, FilmNoleggioDTO.class);

    }



}

package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.repository.FilmRepository;

import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Indica che vogliamo usare Mockito in questo test
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FilmService filmService;

    @Test
    void simpleTest(){
        Assertions.assertTrue(true);
    }

    /********************************
    *   INIZIO TEST GET ALL FILMS    *
    *********************************/

    @Test
    void getAllFilm_ok(){
        FilmModel film1 = new FilmModel();
        FilmModel film2 = new FilmModel();
        var filmList = Arrays.asList(film1,film2);
        when(filmRepository.findAll()).thenReturn(filmList);
        // non puoi mappare ogni singolo elelemnto come nel service
        //when(filmList.stream().map(film -> modelMapper.map(film, FilmDTO.class)).toList()).thenReturn(filmListDto);
        // solo singolarmente
        when(modelMapper.map(film1, FilmDTO.class)).thenReturn(getFilmDto());
        when(modelMapper.map(film2, FilmDTO.class)).thenReturn(getFilmDto());

        var ritornoFilms = filmService.getAllFilms();

        // verifica che non ritorni un valore vuoto
        Assertions.assertNotNull(ritornoFilms);
        // assertEquals Accetta dei numeri
        // primo è il valore ATTESO
        // il secondo il valore da testare
        Assertions.assertEquals(2, ritornoFilms.size());
    }

    /********************************
     *   FINE TEST GET ALL FILMS    *
     ********************************/

    /************************************************************************************/

    /********************************
     *   INIZIO TEST SAVE FILMS     *
     ********************************/


    @Test
    void saveFilm_ok(){
        // indico le azioni da fare
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(getFilmModel()));
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmModel.class))).thenReturn(getFilmModel());

        when(filmRepository.save(ArgumentMatchers.any())).thenReturn(getFilmModel());
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());

        // azioni che deve compiere senza da errore
        var ritorno = filmService.saveFilm(getFilmDto());

        //Verifica che l'oggetto non sia null e sia uguale al DTO
        Assertions.assertNotNull(ritorno);
    }

    @Test
    void saveFilm_ko(){
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(getFilmModel()));
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmModel.class))).thenReturn(getFilmModel());

        // Simula un'eccezione durante il salvataggio
        when(filmRepository.save(ArgumentMatchers.any())).thenThrow(new RuntimeException("Errore durante il salvataggio"));
//      when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());

        // Verifica che venga lanciata l'eccezione attesa
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> filmService.saveFilm(getFilmDto()));
        Assertions.assertEquals("Errore durante il salvataggio", exception.getMessage());
    }

    /********************************
     *   FINE TEST SAVE FILMS       *
     ********************************/

    /************************************************************************************/

    /********************************
     *   INIZIO TEST GET ID FILM    *
     ********************************/


    @Test
    void getFilmById_ok(){
        // gli stiamo specificando che deve ritornare il model del film
        when(filmRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(getFilmModel()));
        // mappiamo i dati in dto
        //RICORDATI DI METTERE SEMPRE ArgumentMatchers.eq("TIPO DI DATO DA RITORNARE") nel mapper
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());
        var ritorno = filmService.getFilmById(1l);

        Assertions.assertNotNull(ritorno);
    }

    /********************************
     *   FINE TEST GET ID FILM      *
     ********************************/


/************************************************************************************/


/******************************************************************
 *     ATTRAVERSO LOMBOK E CON @BUILD SULLA CLASSE RICHIESTA      *
 *     POSSO CREARE UNA BUILD DI DATI FITTIZZI PER CREARE I TEST  *
 *                                                                *
 ******************************************************************/
    private FilmDTO getFilmDto(){
        return FilmDTO.builder().nome("Film Example")
                .casaProduzione("Example Productions")
                .genere("Azione").annouscita("2022")
                .hashFilm("abc123").build();
    }
/********************************************************************/

private FilmModel getFilmModel(){
    return FilmModel.builder().nome("Film Example")
            .casaProduzione("Example Productions")
            .genere("Azione")
            .annouscita("2022")
            .hashFilm("abc123").build();
}














}


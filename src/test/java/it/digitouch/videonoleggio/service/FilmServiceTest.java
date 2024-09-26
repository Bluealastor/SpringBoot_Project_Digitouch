package it.digitouch.videonoleggio.service;

import it.digitouch.videonoleggio.dto.FilmDTO;
import it.digitouch.videonoleggio.exception.ElementAlreadyFoundException;
import it.digitouch.videonoleggio.model.FilmModel;
import it.digitouch.videonoleggio.repository.FilmRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        assertNotNull(ritornoFilms);
        // assertEquals Accetta dei numeri
        // primo è il valore ATTESO
        // il secondo il valore da testare
        assertEquals(2, ritornoFilms.size());
    }

    @Test
    void getAllFilm_ko() {
        FilmModel film1 = new FilmModel();
        FilmModel film2 = new FilmModel();
        var filmList = Arrays.asList(film1, film2);

        // Configurazione del mock per restituire la lista di FilmModel
        when(filmRepository.findAll()).thenReturn(filmList);

        // Simuliamo un errore nel mapping per il secondo film
        when(modelMapper.map(film1, FilmDTO.class)).thenReturn(getFilmDto());
        when(modelMapper.map(film2, FilmDTO.class)).thenThrow(new RuntimeException("Mapping Error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            filmService.getAllFilms();
        });

        // Verifica che il messaggio dell'eccezione sia corretto
        assertEquals("Mapping Error", exception.getMessage());

        // Assicuriamoci che il metodo findAll sia stato chiamato
        verify(filmRepository).findAll();
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
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.empty());
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmModel.class))).thenReturn(getFilmModel());

        when(filmRepository.save(ArgumentMatchers.any())).thenReturn(getFilmModel());
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());

        // azioni che deve compiere senza da errore
        var ritorno = filmService.saveFilm(getFilmDto());

        //Verifica che l'oggetto non sia null e sia uguale al DTO
        assertNotNull(ritorno);
    }

    @Test
    void saveFilm_ko(){
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.empty());
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmModel.class))).thenReturn(getFilmModel());

        // Simula un'eccezione durante il salvataggio
        when(filmRepository.save(ArgumentMatchers.any()))
                .thenThrow(new RuntimeException("Errore durante il salvataggio"));
//      when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());

        // Verifica che venga lanciata l'eccezione attesa
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> filmService.saveFilm(getFilmDto()));
        assertEquals("Errore durante il salvataggio", exception.getMessage());
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

        assertNotNull(ritorno);
    }

    @Test
    public void getFilmById_ko() {
        // Arrange
        when(filmRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(getFilmModel()));
        when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class))).thenReturn(getFilmDto());

        // Act
        FilmDTO result = filmService.getFilmById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(getFilmDto().getId(), result.getId());
        assertEquals(getFilmDto().getNome(), result.getNome());
        verify(filmRepository).findById(ArgumentMatchers.any());
        verify(modelMapper).map(ArgumentMatchers.any(), ArgumentMatchers.eq(FilmDTO.class));
    }


    /********************************
     *   FINE TEST GET ID FILM      *
     ********************************/

/************************************************************************************/

    /********************************
     * INIZIO TEST DELETE ID FILM   *
     ********************************/

    @Test
    void deleteFilmById_ok(){
        filmService.deleteFilmById(2L);
        // verify utilizzato per verificare se il metodo viene chiamato
        // con times indico quante volte deve essere chiamate
        verify(filmRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteFilmById_ko() {

        Long nonExistentFilmId = 1L;

        // RICORDA CHE l'errore viene inserito prima di eliminare
        // NON DOPO CHE HA ESEGUITO L'ELIMINAZIONE
        // SI UTILIZZA DOTHROW
        doThrow(new RuntimeException("Film not found")).when(filmRepository).deleteById(nonExistentFilmId);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            filmService.deleteFilmById(nonExistentFilmId);
        });

        assertEquals("Film not found", exception.getMessage());
    }


    /********************************
     *   FINE TEST DELETE ID FILM   *
     ********************************/

    /********************************************************************/


    /********************************
     * INIZIO TEST UPDATE ID FILM   *
     ********************************/


    @Test
    void updateFilm_success() {
        Long filmId = 1L; // ID del film da aggiornare

        // DTO con i nuovi valori
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setNome("Nuovo Nome");
        filmDTO.setCasaProduzione("Nuova Casa");
        filmDTO.setGenere("Commedia");
        filmDTO.setAnnouscita("2023");

        // FilmModel esistente
        FilmModel existingFilmModel = new FilmModel();
        existingFilmModel.setNome("Vecchio Nome");
        existingFilmModel.setCasaProduzione("Vecchia Casa");
        existingFilmModel.setGenere("Drammatico");
        existingFilmModel.setAnnouscita("2020");

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(existingFilmModel));

//        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.empty());
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenReturn(Optional.empty());

        when(modelMapper.map(existingFilmModel, FilmDTO.class)).thenReturn(filmDTO);

        FilmDTO updatedFilmDTO = filmService.updateFilm(filmId, filmDTO);

        assertNotNull(updatedFilmDTO);

        assertEquals("Nuovo Nome", existingFilmModel.getNome());
        assertEquals("Nuova Casa", existingFilmModel.getCasaProduzione());
        assertEquals("Commedia", existingFilmModel.getGenere());
        assertEquals("2023", existingFilmModel.getAnnouscita());
        assertEquals("TnVvdm8gTm9tZU51b3ZhIENhc2FDb21tZWRpYTIwMjM=", existingFilmModel.getHashFilm());

        verify(filmRepository).save(existingFilmModel);
    }

    @Test
    void updateFilm_ko() {
        Long filmId = 1L;

        // DTO con i nuovi valori
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setNome("Nuovo Nome");
        filmDTO.setCasaProduzione("Nuova Casa");
        filmDTO.setGenere("Commedia");
        filmDTO.setAnnouscita("2023");

        // FilmModel esistente
        FilmModel existingFilmModel = new FilmModel();
        existingFilmModel.setNome("Vecchio Nome");
        existingFilmModel.setCasaProduzione("Vecchia Casa");
        existingFilmModel.setGenere("Drammatico");
        existingFilmModel.setAnnouscita("2020");

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(existingFilmModel));

        // Simuliamo che esista già un film con lo stesso hash
        FilmModel anotherFilmModel = new FilmModel();
        anotherFilmModel.setHashFilm(filmDTO.getHashFilm());
        when(filmRepository.findByhashFilm(ArgumentMatchers.any())).thenThrow(new ElementAlreadyFoundException("Un film con codice TnVvdm8gTm9tZU51b3ZhIENhc2FDb21tZWRpYTIwMjM= esiste già."));

        Exception exception = assertThrows(ElementAlreadyFoundException.class, () -> {
            filmService.updateFilm(filmId, filmDTO);
        });

        assertEquals("Un film con codice " + filmDTO.getHashFilm() + " esiste già.", exception.getMessage());
    }

    /********************************
     *  FINE  TEST UPDATE ID FILM   *
     ********************************/


/******************************************************************
 *     ATTRAVERSO LOMBOK E CON @BUILD SULLA CLASSE RICHIESTA      *
 *     POSSO CREARE UNA BUILD DI DATI FITTIZZI PER CREARE I TEST  *
 *                                                                *
 ******************************************************************/

private FilmDTO getFilmDto() {
    return FilmDTO.builder().nome("Film Example")
                .casaProduzione("Example Productions")
                .genere("Azione").annouscita("2022")
                .hashFilm("abc123").build();
};



    private FilmModel getFilmModel(){
        return FilmModel.builder().nome("Film Example")
                .casaProduzione("Example Productions")
                .genere("Azione")
                .annouscita("2022")
                .hashFilm("abc123").build();
    }














}


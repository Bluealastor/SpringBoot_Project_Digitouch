package it.digitouch.videonoleggio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

  /****************************
  *    @ControllerAdvice      *
  ****************************/

@ControllerAdvice
public class ExceptionGlobal {

     /************************************************************************************************************
     *   @ExceptionHandler('Classe custom exception')                                                            *
     *   Ritornare il response entity della crud con parametro <'ErroroMessageCustom'>(per ritornare un oggetto) *
     *   all return del New response entity ErrorMessage con i dati di ritorno e il tipo di errore               *
     ************************************************************************************************************/
      @ExceptionHandler(ElementNotFoundException.class)
      public ResponseEntity<ExceptionErrorMessage> notFoundException(ElementNotFoundException exception) {
          return new ResponseEntity<>(new ExceptionErrorMessage("Element Not Found", exception.getMessage()), HttpStatus.NOT_FOUND);
      }

      @ExceptionHandler(ElementAlreadyFoundException.class)
      public ResponseEntity<ExceptionErrorMessage> AlreadyFoundException(ElementAlreadyFoundException exception){
          return new ResponseEntity<>(new ExceptionErrorMessage("Elemento Già Esistente",exception.getMessage()), HttpStatus.CONFLICT);
      }


}

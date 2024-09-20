package it.digitouch.videonoleggio.exception;

import lombok.*;

@Data
public class ElementNotFoundException extends RuntimeException {

    private String message;
    public ElementNotFoundException(String elementoNonTrovato) {
    }
}

package it.digitouch.videonoleggio.exception;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ElementAlreadyFoundException extends RuntimeException{

    private String message;

    public ElementAlreadyFoundException(String message) {
        this.message = message;
    }
}

package it.digitouch.videonoleggio.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ElementAlreadyFoundException extends RuntimeException{

    private String message;

    public ElementAlreadyFoundException(String message) {
        this.message = message;
    }
}

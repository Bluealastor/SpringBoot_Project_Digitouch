package it.digitouch.videonoleggio.exception;

import lombok.*;


@NoArgsConstructor
@Getter @Setter
public class ElementNotFoundException extends RuntimeException {

    private String message;

    public ElementNotFoundException(String message) {
        this.message = message;
    }
}

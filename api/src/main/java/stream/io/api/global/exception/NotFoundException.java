package stream.io.api.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RequestError {
    public NotFoundException(String message) {
        super("NotFoundException", message, HttpStatus.NOT_FOUND);
    }    
}
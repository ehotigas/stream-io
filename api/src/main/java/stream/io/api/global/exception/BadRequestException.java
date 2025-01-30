package stream.io.api.global.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RequestError {
    public BadRequestException(String message) {
        super("BadRequestException", message, HttpStatus.BAD_REQUEST);
    }    
}
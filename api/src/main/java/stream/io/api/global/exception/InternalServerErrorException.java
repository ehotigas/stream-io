package stream.io.api.global.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RequestError {
    public InternalServerErrorException(String message) {
        super("InternalServerErrorException", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }    
}

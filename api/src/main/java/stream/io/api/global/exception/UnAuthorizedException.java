package stream.io.api.global.exception;

import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends RequestError {
    public UnAuthorizedException(String message) {
        super("UnAuthorizedException", message, HttpStatus.UNAUTHORIZED);
    }    
}

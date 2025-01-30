package stream.io.api.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import stream.io.api.global.exception.BadRequestException;
import stream.io.api.global.exception.InternalServerErrorException;
import stream.io.api.global.exception.NotFoundException;
import stream.io.api.global.exception.RequestErrorDto;
import stream.io.api.global.exception.UnAuthorizedException;


@ControllerAdvice
public class AdviceHandlerController {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RequestErrorDto> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.internalServerError().body(new RequestErrorDto(e.getError(), e.getMessage(), e.getStatusCode()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<RequestErrorDto> handleInternalServerErrorException(InternalServerErrorException e) {
        return ResponseEntity.internalServerError().body(new RequestErrorDto(e.getError(), e.getMessage(), e.getStatusCode()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RequestErrorDto> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.internalServerError().body(new RequestErrorDto(e.getError(), e.getMessage(), e.getStatusCode()));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<RequestErrorDto> handleUnAuthorizedException(UnAuthorizedException e) {
        return ResponseEntity.internalServerError().body(new RequestErrorDto(e.getError(), e.getMessage(), e.getStatusCode()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RequestErrorDto> handleDefaultException(Exception e) {
        return ResponseEntity.internalServerError().body(new RequestErrorDto("InternalServerErrorException", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

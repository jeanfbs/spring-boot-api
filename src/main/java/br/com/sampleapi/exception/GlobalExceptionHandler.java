package br.com.sampleapi.exception;

import br.com.sampleapi.dto.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error handleDataNotFoundException(final DataNotFoundException ex) {
        return new Error(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(RegisterAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Error handleRegisterAlreadyExistException(final RegisterAlreadyExistException ex) {
        return new Error(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error handleUnprocessableEntityException(final UnprocessableEntityException ex) {
        return new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error genericException(Exception ex) {
        log.error("", ex);
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error has occurred");
    }
}


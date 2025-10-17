package br.com.sampleapi.exception;

import br.com.sampleapi.dto.Error;
import br.com.sampleapi.dto.FieldErrorItem;
import br.com.sampleapi.dto.FieldsErrors;
import feign.FeignException;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        Error response = new Error(HttpStatus.NOT_FOUND.value(), "Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        FieldsErrors.Builder builder = FieldsErrors.builder();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage("field.invalid", null, LocaleContextHolder.getLocale());
            FieldErrorItem item = new FieldErrorItem(e.getField(), message);
            builder.errors(Arrays.asList(item));
        });
        return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), "Bad request"), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(new Error(HttpStatus.BAD_REQUEST.value(), "Bad request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Error methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return new Error(HttpStatus.BAD_REQUEST.value(), "Bad request");
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Error accessDeniedException() {
        return new Error(HttpStatus.FORBIDDEN.value(), "Forbidden");
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleHttpClientException(FeignException ex) {
        log.error("Error while calling {} ", ex.request().url(), ex);
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                MessageFormat.format("Error while calling {0}", ex.request().url()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public Error unprocessableEntityException(final UnprocessableEntityException ex) {
        String message = ex.getMessage();
        if (StringUtils.isBlank(message)) {
            return new Error(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Unprocessable entity");
        }
        return new Error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Error genericException(Exception ex) {
        log.error("", ex);
        return new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error has occurred");
    }
}


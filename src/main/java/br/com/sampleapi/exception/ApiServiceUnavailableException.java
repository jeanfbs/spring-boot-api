package br.com.sampleapi.exception;

public class ApiServiceUnavailableException extends RuntimeException {
    public ApiServiceUnavailableException(String message) {
        super(message);
    }
}

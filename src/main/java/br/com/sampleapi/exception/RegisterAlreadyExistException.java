package br.com.sampleapi.exception;

public class RegisterAlreadyExistException extends RuntimeException {
    public RegisterAlreadyExistException(String message) {
        super(message);
    }
}

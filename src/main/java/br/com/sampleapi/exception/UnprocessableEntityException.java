package br.com.sampleapi.exception;

public class UnprocessableEntityException extends RuntimeException{

    private int code;

    public UnprocessableEntityException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

package fr.iut.sj.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class APIExceptions extends RuntimeException {
    private final HttpStatus status;
    
    public APIExceptions(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
    
    public HttpStatus getStatus() {
        return status;
    }
}
package fr.iut.sj.pkdxapi.errors;

import org.springframework.http.HttpStatus;


public class UserAlreadyExistException extends APIExceptions{
    public UserAlreadyExistException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}

package fr.iut.sj.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class PkmnNotFoundException extends APIExceptions{
    public PkmnNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}

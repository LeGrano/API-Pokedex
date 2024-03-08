package fr.iut.sj.pkdxapi.errors;

import org.springframework.http.HttpStatus;

public class PkmnAlreadyExistsException extends APIExceptions {
        public PkmnAlreadyExistsException(String message) {
            super(HttpStatus.CONFLICT, message);
    }
}

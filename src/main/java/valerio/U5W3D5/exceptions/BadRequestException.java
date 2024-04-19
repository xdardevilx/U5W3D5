package valerio.U5W3D5.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;


    public BadRequestException(List<ObjectError> errorList) {
        super("bad request");
        this.errorList = errorList;
    }

    public BadRequestException(String message) {
        super(message);
    }
}

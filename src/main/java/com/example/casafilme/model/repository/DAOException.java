package com.example.casafilme.model.repository;

public class DAOException extends Exception {

    public DAOException(String message) {
        super(message);
    }
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
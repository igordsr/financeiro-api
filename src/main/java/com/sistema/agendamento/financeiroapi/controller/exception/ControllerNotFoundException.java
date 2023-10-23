package com.sistema.agendamento.financeiroapi.controller.exception;

public class ControllerNotFoundException extends RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}

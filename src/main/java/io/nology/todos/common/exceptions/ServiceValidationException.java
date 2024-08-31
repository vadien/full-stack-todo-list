package io.nology.todos.common.exceptions;

import io.nology.todos.common.ValidationErrors;

public class ServiceValidationException extends Exception {
    private ValidationErrors errors;

    public ServiceValidationException(ValidationErrors errors) {
        this.errors = errors;
    }

    public ValidationErrors getErrors() {
        return errors;
    }

}

package kz.magzhanbeketov.test.exception;

import java.util.function.Supplier;

public class NotFoundException extends Exception {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}

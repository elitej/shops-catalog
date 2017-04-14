package ru.testwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product Not Found")
public class ProductNotFoundException extends RuntimeException {
    private long id;

    public ProductNotFoundException(long id) {
        super(String.format("Product with id = %s not found", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

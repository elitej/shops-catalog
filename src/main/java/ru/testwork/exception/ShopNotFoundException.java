package ru.testwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Shop Not Found")
public class ShopNotFoundException extends RuntimeException {

    private long id;

    public ShopNotFoundException(long id){
        super(String.format("Shop with id = %s not found", id));
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

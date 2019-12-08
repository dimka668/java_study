package com.klyshov.model;

/**
 * Created by 16688641 on 21.11.2019.
 */
import org.springframework.stereotype.Component;

@Component
public class TextResource {

    public String message() {
        return "Message#" + System.currentTimeMillis();
    }
}
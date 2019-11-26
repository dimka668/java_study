package com.klyshov.service;

/**
 * Created by 16688641 on 21.11.2019.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klyshov.model.TextResource;

@Service
public class MessageService {

    private TextResource textResource;

    @Autowired
    public void setTextResource(TextResource textResource) {
        this.textResource = textResource;
    }

    public String textMessage() {
        return textResource.message();
    }
}
package com.klyshov.lombok;

import java.io.File;

/**
 * Created by 16688641 on 30.10.2019.
 */
public class Main {
    public static void main(String[] args) {
        Message message = Message.builder()
                .sender("user@somedomain.com")
                .recipient("someuser@otherdomain.com")
                .text("How are you today?")
                .file(new File("/bla/bla"))
                .build();
        System.out.println(message);
    }
}

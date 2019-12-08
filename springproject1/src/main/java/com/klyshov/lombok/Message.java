package com.klyshov.lombok;

import lombok.Builder;
import lombok.Data;

import java.io.File;

/**
 * Created by 16688641 on 30.10.2019.
 */
@Builder(builderClassName = "MessageBuilder")
@Data
public class Message {
    private String sender;
    private String recipient;
    private String text;
    private File file;

    public static class MessageBuilder {
        private String text;
        private File file;

        public MessageBuilder text(String text) {
            this.text = text;
            verifyTextOrFile();
            return this;
        }

        public MessageBuilder file(File file) {
            this.file = file;
            verifyTextOrFile();
            return this;
        }

        private void verifyTextOrFile() {
            if (text != null && file != null) {
                throw new IllegalStateException("Cannot send 'text' and 'file'.");
            }
        }
    }
}


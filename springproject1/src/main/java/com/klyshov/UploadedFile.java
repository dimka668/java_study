package com.klyshov;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 16688641 on 05.10.2019.
 */
public class UploadedFile {

    private MultipartFile file;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
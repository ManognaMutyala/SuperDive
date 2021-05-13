package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.List;

public class FileList {

    private List<File> files;

    public FileList(List<File> files) {
        this.files = files;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}

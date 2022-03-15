package com.tj.exercise.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tj
 * @Date: 2022/3/15 10:08
 */
public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}

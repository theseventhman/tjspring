package com.tj.exercise.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.tj.exercise.springframework.core.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tj
 * @Date: 2022/3/15 9:57
 */
public class ClassPathResource implements Resource {

    private final String path;

    public ClassPathResource(String path) {
        this(path,(ClassLoader)null);
    }

    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = (classLoader !=null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if(inputStream ==null){
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return inputStream;
    }
}

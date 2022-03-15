package com.tj.exercise.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: tj
 * @Date: 2022/3/15 9:56
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}

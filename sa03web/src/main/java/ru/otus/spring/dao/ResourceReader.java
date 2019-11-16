package ru.otus.spring.dao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

public class ResourceReader {
    private static final String UTF_8 = "UTF-8";

    public static String asString(Resource resource) {
        if(resource==null){
            System.out.println("resource is null");
            return null;
        }
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

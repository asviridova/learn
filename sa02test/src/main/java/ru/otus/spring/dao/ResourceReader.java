package ru.otus.spring.dao;


//import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

//@Slf4j
public class ResourceReader {
    public static String UTF_8 = "UTF-8";

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

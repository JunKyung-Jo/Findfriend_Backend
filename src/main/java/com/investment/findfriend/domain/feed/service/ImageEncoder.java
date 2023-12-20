package com.investment.findfriend.domain.feed.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ImageEncoder {
    public String encode(String url) throws IOException, URISyntaxException {
        Path path = Paths.get(new URL(url).toURI());
        byte[] imageBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}

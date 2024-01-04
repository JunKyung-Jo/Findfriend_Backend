package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.feed.exception.FileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileSaveUtil {

    @Value("${root.path}")
    private Path rootPath;

    public String save(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            System.out.println(fileName);
            Path path = rootPath.resolve(
                    Paths.get(fileName)
                            .normalize());
            System.out.println(path);
            Files.createFile(path);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("success");
            return String.format("%s/%s", rootPath, fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw FileException.EXCEPTION;
        }
    }
}

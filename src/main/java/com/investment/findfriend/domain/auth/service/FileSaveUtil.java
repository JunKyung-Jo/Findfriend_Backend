package com.investment.findfriend.domain.auth.service;

import com.investment.findfriend.domain.feed.exception.FileSaveException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileSaveUtil {

    @Value("${ROOT_PATH}")
    private Path rootPath;

    public String save(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = rootPath.resolve(
                    Paths.get(fileName)
                            .normalize()
                            .toAbsolutePath());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return String.format("%s/%s", rootPath, fileName);
        } catch (IOException e) {
            throw FileSaveException.EXCEPTION;
        }
    }
}

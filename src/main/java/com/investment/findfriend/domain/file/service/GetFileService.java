package com.investment.findfriend.domain.file.service;

import com.investment.findfriend.domain.feed.exception.FileNotFoundException;
import com.investment.findfriend.domain.file.domain.File;
import com.investment.findfriend.domain.file.repository.FileRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class GetFileService {

    private final FileRepository fileRepository;

    public ResponseEntity<Resource> execute(Long fileId, HttpServletRequest httpServletRequest) throws IOException {
        // 파일 저장된 기록 검색
        File file = fileRepository.findById(fileId).orElseThrow(
                () -> FileNotFoundException.EXCEPTION
        );
        // 저장된 경로를 통해 절대경로 생성
        Path path = Paths.get(file.getPath());
        // 파일 가져오기
        Resource resource = new UrlResource(path.toUri());
        String contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        // 파일 return
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(contentType)
                .body(resource);
    }
}

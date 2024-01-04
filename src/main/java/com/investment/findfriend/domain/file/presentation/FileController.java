package com.investment.findfriend.domain.file.presentation;

import com.investment.findfriend.domain.file.service.GetFileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final GetFileService getFileService;

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestParam Long fileId, HttpServletRequest httpServletRequest) throws IOException {
        return getFileService.execute(fileId, httpServletRequest);
    }
}

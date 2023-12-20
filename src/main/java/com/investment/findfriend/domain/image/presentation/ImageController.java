package com.investment.findfriend.domain.image.presentation;

import com.investment.findfriend.domain.image.service.GetImageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final GetImageService getImageService;

    @GetMapping
    public ResponseEntity<Resource> getImage(@RequestParam Long feedId, HttpServletRequest httpServletRequest) throws IOException {
        return getImageService.execute(feedId, httpServletRequest);
    }
}

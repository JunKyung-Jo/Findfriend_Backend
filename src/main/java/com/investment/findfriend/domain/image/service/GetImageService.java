package com.investment.findfriend.domain.image.service;

import com.investment.findfriend.domain.feed.domain.Feed;
import com.investment.findfriend.domain.feed.repository.FeedRepository;
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
public class GetImageService {

    private final FeedRepository feedRepository;

    public ResponseEntity<Resource> execute(Long feedId, HttpServletRequest httpServletRequest) throws IOException {
        Feed feed = feedRepository.findById(feedId).orElseThrow(
//                () -> FeedNotFoundException
        );
        Path path = Paths.get(feed.getUrl());
        Resource resource = new UrlResource(path.toUri());
        String contentType = httpServletRequest.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(contentType)
                .body(resource);
    }
}

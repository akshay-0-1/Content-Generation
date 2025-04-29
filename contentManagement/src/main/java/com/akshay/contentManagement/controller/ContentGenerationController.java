package com.akshay.contentManagement.controller;

import com.akshay.contentManagement.service.ContentGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/content")
public class ContentGenerationController {
    private final ContentGenerationService contentGenerationService;

    public ContentGenerationController(ContentGenerationService contentGenerationService) {
        this.contentGenerationService = contentGenerationService;
    }

    @GetMapping
    public ResponseEntity<String> generateContent(
            @RequestParam String type,
            @RequestParam String input) {

        return ResponseEntity.ok(contentGenerationService.generateContent(type.toLowerCase(), input));
    }
}
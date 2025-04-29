package com.akshay.contentManagement.controller;

import com.akshay.contentManagement.service.ContentGenerationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContentGenerationController {

    private final ContentGenerationService contentGenerationService;

    public ContentGenerationController(ContentGenerationService contentGenerationService) {
        this.contentGenerationService = contentGenerationService;
    }

    @GetMapping("/notes")
    public ResponseEntity<String> getNotes(@RequestParam String topic) {
        return ResponseEntity.ok(contentGenerationService.generateNotes(topic));
    }

    @GetMapping("/caption")
    public ResponseEntity<String> getCaption(@RequestParam String topic) {
        return ResponseEntity.ok(contentGenerationService.generateCaption(topic));
    }

    @GetMapping("/blog")
    public ResponseEntity<String> getBlog(@RequestParam String topic) {
        return ResponseEntity.ok(contentGenerationService.generateBlog(topic));
    }

    @PostMapping("/summary")
    public ResponseEntity<String> getSummary(@RequestBody String text) {
        return ResponseEntity.ok(contentGenerationService.generateSummary(text));
    }
}

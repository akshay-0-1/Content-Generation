package com.akshay.contentManagement.controller;

import com.akshay.contentManagement.repository.SearchHistoryRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final SearchHistoryRepository historyRepo;

    public HistoryController(SearchHistoryRepository historyRepo) {
        this.historyRepo = historyRepo;
    }

    @GetMapping
    public ResponseEntity<?> getUserHistory(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first");

        return ResponseEntity.ok(historyRepo.findByUserId(userId));
    }
}


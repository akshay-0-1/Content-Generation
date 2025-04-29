package com.akshay.contentManagement.repository;

import com.akshay.contentManagement.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    List<SearchHistory> findByUserId(Long userId);
    List<SearchHistory> findByUser_IdOrderByTimestampDesc(Long userId);
}
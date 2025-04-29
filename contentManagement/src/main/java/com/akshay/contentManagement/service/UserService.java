package com.akshay.contentManagement.service;

import com.akshay.contentManagement.entity.SearchHistory;
import com.akshay.contentManagement.entity.User;
import com.akshay.contentManagement.repository.SearchHistoryRepository;
import com.akshay.contentManagement.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    @Autowired
    public UserService(UserRepository userRepository, SearchHistoryRepository searchHistoryRepository) {
        this.userRepository = userRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public boolean login(String username, String password, HttpSession session) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            return true;
        }
        return false;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public void saveSearchHistory(Long userId, String type, String input) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) return;

        User user = userOptional.get();

        SearchHistory history = new SearchHistory();
        history.setType(type);
        history.setInput(input);
        history.setTimestamp(LocalDateTime.now());
        history.setUser(user);  // set User entity

        searchHistoryRepository.save(history);
    }

    public List<SearchHistory> getSearchHistory(Long userId) {
        return searchHistoryRepository.findByUser_IdOrderByTimestampDesc(userId);
    }
}

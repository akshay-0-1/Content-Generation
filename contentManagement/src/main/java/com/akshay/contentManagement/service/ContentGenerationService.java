package com.akshay.contentManagement.service;

import com.akshay.contentManagement.util.GeminiResponseParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class ContentGenerationService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro-002:generateContent?key=";

    public ContentGenerationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Common Gemini API caller
    private String callGeminiAPI(String prompt) {
        String requestBody = "{"
                + "\"contents\": ["
                + "  {"
                + "    \"parts\": ["
                + "      {"
                + "        \"text\": \"" + prompt + "\""
                + "      }"
                + "    ]"
                + "  }"
                + "]"
                + "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                API_URL + apiKey,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        return GeminiResponseParser.extractText(responseEntity.getBody());
    }

    // Individual services
    public String generateNotes(String topic) {
        String prompt = "Give a short, simple note about '" + topic + "'.";
        return callGeminiAPI(prompt);
    }

    public String generateCaption(String topic) {
        String prompt = "Write a creative Instagram caption for: '" + topic + "'.";
        return callGeminiAPI(prompt);
    }

    public String generateBlog(String topic) {
        String prompt = "Write a detailed blog post about: '" + topic + "'.";
        return callGeminiAPI(prompt);
    }

    public String generateSummary(String text) {
        String prompt = "Summarize the following content in a few lines: '" + text + "'.";
        return callGeminiAPI(prompt);
    }
}

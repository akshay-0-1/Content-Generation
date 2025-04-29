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

    public String generateContent(String type, String input) {
        String prompt;
        switch (type) {
            case "notes":
                prompt = "Give a short, simple note about '" + input + "'.";
                break;
            case "caption":
                prompt = "Write a creative Instagram caption for: '" + input + "'.";
                break;
            case "blog":
                prompt = "Write a detailed blog post about: '" + input + "'.";
                break;
            case "summary":
                prompt = "Summarize the following content in a few lines: '" + input + "'.";
                break;
            default:
                throw new IllegalArgumentException("Unsupported content type: " + type);
        }

        return callGeminiAPI(prompt);
    }

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
}

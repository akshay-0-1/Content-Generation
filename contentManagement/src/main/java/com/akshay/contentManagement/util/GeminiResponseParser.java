package com.akshay.contentManagement.util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeminiResponseParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String extractText(String responseJson) {
        try {
            JsonNode root = objectMapper.readTree(responseJson);
            JsonNode candidates = root.path("candidates");
            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode firstCandidate = candidates.get(0);
                JsonNode textNode = firstCandidate.path("content").path("parts").get(0).path("text");
                return textNode.asText();
            } else {
                return "No content found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing response";
        }
    }
}

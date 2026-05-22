package com.example.salonexplorer.backend.client;

import com.example.salonexplorer.backend.client.dto.GooglePlacesResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GooglePlacesClient {

    @Value("${google.api}")
    private String apiKey;

    public GooglePlacesResponseDto searchForSalons(String textQuery) {
        String url = "https://places.googleapis.com/v1/places:searchText";
        HttpHeaders headers = getHeaders();
        String requestBody = getRequestBody(textQuery);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GooglePlacesResponseDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                GooglePlacesResponseDto.class
        );
        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", apiKey);
        headers.set(
                "X-Goog-FieldMask",
                "places.displayName," +
                        "places.rating," +
                        "places.userRatingCount," +
                        "places.websiteUri," +
                        "places.nationalPhoneNumber," +
                        "places.priceLevel," +
                        "places.id," +
                        "places.types," +
                        "places.googleMapsUri," +
                        "places.addressComponents," +
                        "places.photos"
        );
        return headers;
    }

    private String getRequestBody(String textQuery) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("textQuery", textQuery);
        try {
            return mapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize request body", e);
        }
    }
}

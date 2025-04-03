package org.untoc_camp.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SolvedAcClient {
    private final WebClient webClient = WebClient.create("https://solved.ac/api/v3");

    public String getBioByHandle(String handle) {
        try {
            return webClient.get()
                    .uri("/user/show?handle=" + handle)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(json -> json.get("bio").asText())
                    .block();
        } catch (Exception e) {
            return null;
        }
    }
}


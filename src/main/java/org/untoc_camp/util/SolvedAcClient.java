package org.untoc_camp.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.untoc_camp.dto.ranking.SolvedAcUserStatsDto;

import java.util.Optional;

@Component
@Slf4j
public class SolvedAcClient {
    private final WebClient webClient = WebClient.create("https://solved.ac/api/v3");

    public Optional<SolvedAcUserStatsDto> fetchInfo(String handle) {
        try {
            JsonNode json = webClient.get()
                    .uri("/user/show?handle=" + handle)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            SolvedAcUserStatsDto info = new SolvedAcUserStatsDto();
            info.setRating(json.get("rating").asLong());
            info.setTier(json.get("tier").asInt());
            info.setRank(json.get("rank").asInt());
            info.setSolvedCount(json.get("solvedCount").asInt());

            return Optional.of(info);
        } catch (Exception e) {
            log.warn("Solved.ac fetch 실패 for {}: {}", handle, e.getMessage());
            return Optional.empty();
        }
    }

    public String getBioByHandle(String handle) {
        try {
            return webClient.get()
                    .uri("/user/show?handle=" + handle)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(json -> json.get("bio").asText())
                    .block();
        } catch (Exception e) {
            log.warn("getBio 실패 for {}: {}", handle, e.getMessage());
            return null;
        }
    }
}

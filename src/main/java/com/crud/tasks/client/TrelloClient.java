package com.crud.tasks.client;

import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Value;
import java.net.URI;
import java.util.*;
import lombok.*;
 @Component
@RequiredArgsConstructor
public class TrelloClient {
    private  final RestTemplate restTemplate;
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUsername;
    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = buildTrelloUrl(trelloApiEndpoint + "/members/daniellon17axb/boards");

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
     private URI buildTrelloUrl(String path) {
         return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + path)
                 .queryParam("key", trelloAppKey)
                 .queryParam("token", trelloToken)
                 .queryParam("fields", "name,id")
                 .build()
                 .encode()
                 .toUri();
     }
}

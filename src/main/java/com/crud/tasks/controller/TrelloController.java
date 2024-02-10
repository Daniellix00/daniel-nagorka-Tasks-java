package com.crud.tasks.controller;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

     List<TrelloBoardDto>filteredBoard = trelloBoards.stream()
             .filter(board->board.getId()!=null && board.getName()!=null)
             .filter(board->board.getName().contains("Kodilla"))
             .collect(Collectors.toList());
for (TrelloBoardDto board: filteredBoard){
    System.out.println(board);
}
    }
}

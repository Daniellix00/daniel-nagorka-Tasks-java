package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloMapperTest {
    @Test
    public void testMaptoBoards(){
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloBoardDto boardDto = new TrelloBoardDto("boardId", "boardName", null);
        if (boardDto.getLists() == null) {
            boardDto.setLists(new ArrayList<>());
        }
        TrelloListDto listDto = new TrelloListDto("listId", "listName", false);
        boardDto.getLists().add(listDto);
        //When
        List<TrelloBoard>boards = mapper.mapToBoards(Arrays.asList(boardDto));
        //Then
        assertEquals(1, boards.size());
        TrelloBoard board = boards.get(0);
        assertEquals("boardId", board.getId());
        assertEquals("boardName", board.getName());
        assertEquals(1, board.getLists().size());
        assertEquals("listId", board.getLists().get(0).getId());
        assertEquals("listName", board.getLists().get(0).getName());
        assertEquals(false, board.getLists().get(0).isClosed());
    }
    @Test
    public void  testMapToBoardsDto(){
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloBoard board = new TrelloBoard("boardId", "Board Name", null);
        if (board.getLists() == null) {
            board.setLists(new ArrayList<>());
        }
        TrelloList list = new TrelloList("listId", "List Name", false);
        board.getLists().add(list);
        //When
        List<TrelloBoardDto> boardDtos = mapper.mapToBoardsDto(Arrays.asList(board));
        //Then
        assertEquals(1, boardDtos.size());
        TrelloBoardDto boardDto = boardDtos.get(0);
        assertEquals("boardId", boardDto.getId());
        assertEquals("Board Name", boardDto.getName());
        assertEquals(1, boardDto.getLists().size());
        assertEquals("listId", boardDto.getLists().get(0).getId());
        assertEquals("List Name", boardDto.getLists().get(0).getName());
        assertEquals(false, boardDto.getLists().get(0).isClosed());

    }
    }


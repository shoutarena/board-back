package com.study.boardback.service;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.response.board.PostBoardResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto postBoardRequestDto, String email);

}

package com.study.boardback.service;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.response.board.GetBoardResponseDto;
import com.study.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.study.boardback.dto.response.board.PostBoardResponseDto;
import com.study.boardback.dto.response.board.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto postBoardRequestDto, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(int boardIdx, String email);

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(int boardIdx);

}

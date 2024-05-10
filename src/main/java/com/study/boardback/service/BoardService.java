package com.study.boardback.service;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.request.board.PostCommentRequestDto;
import com.study.boardback.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto postBoardRequestDto, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardIdx, String email);

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardIdx);

    ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardIdx, PostCommentRequestDto requestBody, String email);

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardIdx);
}

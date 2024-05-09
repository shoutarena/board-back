package com.study.boardback.controller;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.response.board.GetBoardResponseDto;
import com.study.boardback.dto.response.board.PostBoardResponseDto;
import com.study.boardback.dto.response.board.PutFavoriteResponseDto;
import com.study.boardback.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@Tag(name = "Board", description = "Board Controller")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판 등록", description = "게시판 등록 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = { PostBoardRequestDto.class })
                    )
            ),
            responses ={
                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody, @AuthenticationPrincipal String email){
        return boardService.postBoard(requestBody, email);
    }
    
    @Operation(summary = "게시판 상세", description = "게시판 상세 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            }
    )
    @GetMapping(value = "{boardIdx}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable(value = "boardIdx") int boardIdx){
        return boardService.getBoard(boardIdx);
    }

    @Operation(summary = "좋아요", description = "좋아요 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            responses = {
                @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "NM", description = "This user does not exist.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "NB", description = "This board does not exist.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            }
    )
    @PutMapping(value = "/{boardIdx}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(@PathVariable(value = "boardIdx") int boardIdx, @AuthenticationPrincipal String email){
        return boardService.putFavorite(boardIdx, email);
    }

}

package com.study.boardback.controller;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.response.board.PostBoardResponseDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = "application/json"))
            }
    )
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody, @AuthenticationPrincipal String email){
        return boardService.postBoard(requestBody, email);
    }
}

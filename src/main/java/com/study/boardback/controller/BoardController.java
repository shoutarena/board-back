package com.study.boardback.controller;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.request.board.PostCommentRequestDto;
import com.study.boardback.dto.response.board.*;
import com.study.boardback.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@Tag(name = "Board", description = "Board Rest API Controller")
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
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody, @AuthenticationPrincipal String email){
        return boardService.postBoard(requestBody, email);
    }

    @Operation(summary = "게시물 삭제", description = "게시물 삭제 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                    @ApiResponse(responseCode = "NP", description = "Do not hav permission."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @DeleteMapping("/{boardIdx}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx
            , @AuthenticationPrincipal String email){
        return boardService.deleteBoard(boardIdx, email);
    }
    
    @Operation(summary = "게시판 상세", description = "게시판 상세 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success.",
                            content = @Content(
                                    schema = @Schema(implementation = GetBoardResponseDto.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)
                            ),
                    @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping(value = "{boardIdx}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx){
        return boardService.getBoard(boardIdx);
    }

    @Operation(summary = "좋아요", description = "좋아요 API",
            responses = {
                @ApiResponse(responseCode = "SU", description = "Success."),
                @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PutMapping(value = "/{boardIdx}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx,
            @AuthenticationPrincipal String email){
        return boardService.putFavorite(boardIdx, email);
    }

    @Operation(summary = "좋아요 리스트 조회", description = "좋아요 리스트 조회 API",
            responses = {
                @ApiResponse(responseCode = "SU", description = "Success."),
                @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping(value = "/{boardIdx}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx){
        return boardService.getFavoriteList(boardIdx);
    }

    @Operation(summary = "댓글작성", description = "댓글 작성 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {PostCommentResponseDto.class})
                    )
            ),
            responses = {
                @ApiResponse(responseCode = "SU", description = "Success."),
                @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PostMapping("/{boardIdx}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx
            , @Valid @RequestBody PostCommentRequestDto requestBody
            , @AuthenticationPrincipal String email){
        return boardService.postComment(boardIdx, requestBody, email);
    }

    @Operation(summary = "댓글 조회", description = "댓글 조회 API",
            responses = {
                @ApiResponse(responseCode = "SU", description = "Success."),
                @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping("/{boardIdx}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx){
        return boardService.getCommentList(boardIdx);
    }

    @Operation(summary = "게시물 조회수 증가" , description = "게시물 조회수 증가 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NB", description = "This board does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PatchMapping("/{boardIdx}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
            @Parameter(name = "boardIdx", description = "게시물 번호", in = ParameterIn.PATH) @PathVariable(value = "boardIdx") Integer boardIdx) {
        return boardService.increaseViewCount(boardIdx);
    }

}

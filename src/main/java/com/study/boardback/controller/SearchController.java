package com.study.boardback.controller;

import com.study.boardback.dto.response.search.GetPopularListResponseDto;
import com.study.boardback.dto.response.search.GetRelationListResponseDto;
import com.study.boardback.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Search", description = "Search Rest API Controller")
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "인기 검색어 조회", description = "인기 검색어 조회 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList(){
        return searchService.getPopularList();
    }

    @Operation(summary = "관련 검색어 리스트", description = "관련 검색어 리스트 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(
            @Parameter(name = "searchWord", description = "검색어", in = ParameterIn.PATH) @PathVariable(value = "searchWord") String searchWord){
        return searchService.getRelationList(searchWord);
    }

}

package com.study.boardback.dto.response.search;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.repository.resultSet.GetPopularListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetPopularListResponseDto extends ResponseDto {
    private List<String> popularWordList;

    public GetPopularListResponseDto(List<GetPopularListResultSet> popularWordList) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.popularWordList = popularWordList.stream().map(GetPopularListResultSet::getSearchWord).collect(Collectors.toList());
    }

    public static ResponseEntity<GetPopularListResponseDto> success(List<GetPopularListResultSet> popularWordList){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetPopularListResponseDto(popularWordList));
    }
}

package com.study.boardback.dto.response.search;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.repository.resultSet.GetRelationListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetRelationListResponseDto extends ResponseDto {

    private List<String> relativeWordList;

    private GetRelationListResponseDto(List<GetRelationListResultSet> list) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.relativeWordList = list.stream().map(GetRelationListResultSet::getRelationWord).collect(Collectors.toList());
    }

    public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> relationListResultSets){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetRelationListResponseDto(relationListResultSets));
    }

}

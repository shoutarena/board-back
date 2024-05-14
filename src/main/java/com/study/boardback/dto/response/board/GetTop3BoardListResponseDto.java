package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.object.BoardList;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetTop3BoardListResponseDto extends ResponseDto {

    private List<BoardList> top3List;

    public GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.top3List = BoardList.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetTop3BoardListResponseDto(boardListViewEntities));
    }
}

package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.object.BoardList;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.BoardListView;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {

    private List<BoardList> searchList;

    private GetSearchBoardListResponseDto(List<BoardListView> boardListViewEntities) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.searchList = BoardList.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListView> boardListViewEntities) {
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetSearchBoardListResponseDto(boardListViewEntities));
    }

}

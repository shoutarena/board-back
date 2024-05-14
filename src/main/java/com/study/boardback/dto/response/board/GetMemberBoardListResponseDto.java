package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.object.BoardList;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMemberBoardListResponseDto extends ResponseDto {
    private List<BoardList> memberBoardList;
    public GetMemberBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.memberBoardList = BoardList.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetMemberBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetMemberBoardListResponseDto(boardListViewEntities));
    }
}

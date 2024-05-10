package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.object.CommentList;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.repository.resultSet.GetCommentListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetCommentListResponseDto extends ResponseDto {

    private List<CommentList> commentList;

    private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.commentList = CommentList.copyList(resultSets);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSets){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetCommentListResponseDto(resultSets));
    }

}

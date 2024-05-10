package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PostCommentResponseDto extends ResponseDto {

    private PostCommentResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseEntity<ResponseDto> noExistsBoard(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_BOARD);
    }

    public static ResponseEntity<ResponseDto> noExistsMember(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_MEMBER);
    }

}

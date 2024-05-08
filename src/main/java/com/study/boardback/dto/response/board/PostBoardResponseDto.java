package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PostBoardResponseDto extends ResponseDto {

    public PostBoardResponseDto(String Code, String message) {
        super(Code, message);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_MEMBER);
    }

}

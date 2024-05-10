package com.study.boardback.dto.response.board;

import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class PostBoardResponseDto extends ResponseDto {

    public PostBoardResponseDto(String Code, String message) {
        super(Code, message);
    }

}

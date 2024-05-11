package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class DeleteBoardResponseDto extends ResponseDto {

    private DeleteBoardResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

}

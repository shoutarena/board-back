package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {

    private PutFavoriteResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

}

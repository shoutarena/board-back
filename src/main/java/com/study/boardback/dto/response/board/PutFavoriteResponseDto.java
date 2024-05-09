package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {

    private PutFavoriteResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseEntity<ResponseDto> noExistBoard(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_BOARD);
    }

    public static ResponseEntity<ResponseDto> noExistMember(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_MEMBER);
    }

}

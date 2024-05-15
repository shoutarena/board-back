package com.study.boardback.dto.response.member;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {

    private PatchNicknameResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseEntity<ResponseDto> duplicateNickname(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.DUPLICATE_NICKNAME);
    }

}

package com.study.boardback.dto.response.member;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto {

    private PatchProfileImageResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

}

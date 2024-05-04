package com.study.boardback.dto.response.auth;


import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {
    private SignUpResponseDto() {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseEntity<ResponseDto> duplicateEmail() {
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.DUPLICATE_NICKNAME);
    }

    public static ResponseEntity<ResponseDto> duplicateTelNumber() {
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.DUPLICATE_TEL_NUMBER);
    }

}

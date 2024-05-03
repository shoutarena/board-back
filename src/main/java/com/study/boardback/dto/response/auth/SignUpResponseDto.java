package com.study.boardback.dto.response.auth;


import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {
    private SignUpResponseDto(String Code, String message) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    public static ResponseEntity<SignUpRequestDto> success() {
        SignUpRequestDto result = new SignUpRequestDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> duplicateEmail() {
        return ResponseDto.getResponseEntityWithResponseCode(ResponseCode.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        return ResponseDto.getResponseEntityWithResponseCode(ResponseCode.DUPLICATE_NICKNAME);
    }

    public static ResponseEntity<ResponseDto> duplicateTelNumber() {
        return ResponseDto.getResponseEntityWithResponseCode(ResponseCode.DUPLICATE_TEL_NUMBER);
    }

}

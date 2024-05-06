package com.study.boardback.dto.response.auth;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

    private String token;
    private int expirationTime;

    public SignInResponseDto(String token) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success(String token){
        SignInResponseDto result = new SignInResponseDto(token);
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(result);
    }

    public static ResponseEntity<ResponseDto> signInFail(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.SIGN_IN_FAIL);
    }




}

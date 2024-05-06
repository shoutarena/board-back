package com.study.boardback.dto.response;

import com.study.boardback.common.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String Code;
    private String message;

    public static ResponseEntity<ResponseDto> success(){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new ResponseDto(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage()));
    }

    public static ResponseEntity<ResponseDto> getResponseEntityByResponseCode(ResponseCode responseCode){
        return ResponseEntity.status(responseCode.getHttpStatus()).body(new ResponseDto(responseCode.getCode(), responseCode.getMessage()));
    }

    public static ResponseEntity<ResponseDto> databaseError(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR.getCode(), ResponseCode.DATABASE_ERROR.getMessage());
        return ResponseEntity.status(ResponseCode.DATABASE_ERROR.getHttpStatus()).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFailed(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.VALIDATION_FAILED);
    }

}

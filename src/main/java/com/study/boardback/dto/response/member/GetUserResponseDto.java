package com.study.boardback.dto.response.member;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.MemberEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class GetUserResponseDto extends ResponseDto {

    private String email;
    private String nickname;
    private String profileImage;

    private GetUserResponseDto(MemberEntity memberEntity){
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.profileImage = memberEntity.getProfileImage();
    }

    public static ResponseEntity<GetUserResponseDto> success(MemberEntity memberEntity){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetUserResponseDto(memberEntity));
    }

}

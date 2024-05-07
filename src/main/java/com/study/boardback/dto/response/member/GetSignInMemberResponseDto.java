package com.study.boardback.dto.response.member;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.MemberEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInMemberResponseDto extends ResponseDto {
    private String email;
    private String nickname;
    private String profileImage;

    private GetSignInMemberResponseDto(MemberEntity memberEntity){
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.email = memberEntity.getEmail();
        this.nickname = memberEntity.getNickname();
        this.profileImage = memberEntity.getProfileImage();
    }

    public static ResponseEntity<GetSignInMemberResponseDto> success(MemberEntity memberEntity){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetSignInMemberResponseDto(memberEntity));
    }

    public static ResponseEntity<ResponseDto> notExistMember() {
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_MEMBER);
    }

}

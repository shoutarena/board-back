package com.study.boardback.dto.response.member;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.Member;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSignInMemberResponseDto extends ResponseDto {
    private String email;
    private String nickname;
    private String profileImage;

    private GetSignInMemberResponseDto(Member member){
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileImage = member.getProfileImage();
    }

    public static ResponseEntity<GetSignInMemberResponseDto> success(Member member){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetSignInMemberResponseDto(member));
    }

    public static ResponseEntity<ResponseDto> notExistMember() {
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_MEMBER);
    }

}

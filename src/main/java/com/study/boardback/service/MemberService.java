package com.study.boardback.service;

import com.study.boardback.dto.request.member.PatchNicknameRequestDto;
import com.study.boardback.dto.request.member.PatchProfileRequestDto;
import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import com.study.boardback.dto.response.member.PatchNicknameResponseDto;
import com.study.boardback.dto.response.member.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<? super GetSignInMemberResponseDto> getSignInMember(String email);

    ResponseEntity<? super GetUserResponseDto> getUserByEmail(String email);

    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto patchUserRequestDto, String email);

    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileRequestDto patchProfileRequestDto, String email);
}

package com.study.boardback.service;

import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<? super GetSignInMemberResponseDto> getSignInMember(String email);

    ResponseEntity<? super GetUserResponseDto> getUserByEmail(String email);
}

package com.study.boardback.service;

import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<? super GetSignInMemberResponseDto> getSignInMember(String email);
}

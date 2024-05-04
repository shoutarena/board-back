package com.study.boardback.service;

import com.study.boardback.dto.request.auth.SignInRequestDto;
import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.auth.SignInResponseDto;
import com.study.boardback.dto.response.auth.SignUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}

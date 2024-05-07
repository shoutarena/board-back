package com.study.boardback.service.implement;

import com.study.boardback.config.provider.JwtProvider;
import com.study.boardback.dto.request.auth.SignInRequestDto;
import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.auth.SignInResponseDto;
import com.study.boardback.dto.response.auth.SignUpResponseDto;
import com.study.boardback.entity.MemberEntity;
import com.study.boardback.repository.MemberRepository;
import com.study.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * * Auth Service
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            // * Validation
            String email = dto.getEmail();
            boolean existedEmail = memberRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = memberRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = memberRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            // * password 암호화
            String password = dto.getPassword(); // 평문 password
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            // * 객체 복사
            MemberEntity memberEntity = new MemberEntity(dto);

            // * 저장
            memberRepository.save(memberEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {

            String email = dto.getEmail();
            MemberEntity memberEntity = memberRepository.findByEmail(email);

            // * 계정 존재 validation
            if(ObjectUtils.isEmpty(memberEntity)){
                return SignInResponseDto.signInFail();
            }

            // * 비밀번호 체크
            String password = dto.getPassword();
            String encodedPassword = memberEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched){
                return SignInResponseDto.signInFail();
            }

            // * 토큰 생성
            token = jwtProvider.create(email);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}

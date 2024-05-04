package com.study.boardback.service.implement;

import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.auth.SignUpResponseDto;
import com.study.boardback.entity.MemberEntity;
import com.study.boardback.repository.MemberRepository;
import com.study.boardback.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Sinup Service
      * @param dto
     * @return
     */
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            // Validation
            String email = dto.getEmail();
            boolean existedEmail = memberRepository.existsByEmail(email);
            if(existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = memberRepository.existsByNickname(nickname);
            if(existedNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = memberRepository.existsByTelNumber(telNumber);
            if(existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            // password 암호화
            String password = dto.getPassword(); // 평문 password
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            // 객체 복사
            MemberEntity memberEntity = new MemberEntity(dto);

            // 저장
            memberRepository.save(memberEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();

    }
}
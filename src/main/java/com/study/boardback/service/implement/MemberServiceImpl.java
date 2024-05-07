package com.study.boardback.service.implement;

import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.entity.MemberEntity;
import com.study.boardback.repository.MemberRepository;
import com.study.boardback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<? super GetSignInMemberResponseDto> getSignInMember(String email) {

        MemberEntity memberEntity = null;

        try {
            memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return GetSignInMemberResponseDto.notExistMember();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInMemberResponseDto.success(memberEntity);
    }
}

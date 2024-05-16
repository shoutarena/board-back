package com.study.boardback.service.implement;

import com.study.boardback.dto.request.member.PatchNicknameRequestDto;
import com.study.boardback.dto.request.member.PatchProfileRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import com.study.boardback.dto.response.member.PatchNicknameResponseDto;
import com.study.boardback.dto.response.member.PatchProfileImageResponseDto;
import com.study.boardback.entity.Member;
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

        Member member = null;

        try {
            member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return GetSignInMemberResponseDto.notExistMember();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInMemberResponseDto.success(member);
    }

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUserByEmail(String email) {

        Member member = null;
        try {
           member = memberRepository.findByEmail(email);
           if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(member);
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto patchUserRequestDto, String email) {

        try {
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();

            String nickname = patchUserRequestDto.getNickname();
            boolean existedNickname = memberRepository.existsByNickname(nickname);
            if(existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            member.setNickname(nickname);
            memberRepository.save(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileRequestDto patchProfileRequestDto, String email) {
        try {
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();
            member.setProfileImage(patchProfileRequestDto.getProfileImage());
            memberRepository.save(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}

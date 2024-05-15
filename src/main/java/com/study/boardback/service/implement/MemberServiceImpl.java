package com.study.boardback.service.implement;

import com.study.boardback.dto.request.member.PatchNicknameRequestDto;
import com.study.boardback.dto.request.member.PatchProfileRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import com.study.boardback.dto.response.member.PatchNicknameResponseDto;
import com.study.boardback.dto.response.member.PatchProfileImageResponseDto;
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

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUserByEmail(String email) {

        MemberEntity memberEntity = null;
        try {
           memberEntity = memberRepository.findByEmail(email);
           if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetUserResponseDto.success(memberEntity);
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto patchUserRequestDto, String email) {

        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();

            String nickname = patchUserRequestDto.getNickname();
            boolean existedNickname = memberRepository.existsByNickname(nickname);
            if(existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            memberEntity.setNickname(nickname);
            memberRepository.save(memberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileRequestDto patchProfileRequestDto, String email) {
        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();
            memberEntity.setProfileImage(patchProfileRequestDto.getProfileImage());
            memberRepository.save(memberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}

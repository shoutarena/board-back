package com.study.boardback.controller;

import com.study.boardback.dto.request.member.PatchNicknameRequestDto;
import com.study.boardback.dto.request.member.PatchProfileRequestDto;
import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import com.study.boardback.dto.response.member.PatchNicknameResponseDto;
import com.study.boardback.dto.response.member.PatchProfileImageResponseDto;
import com.study.boardback.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "Member Rest API Controller")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 정보 조회", description = "회원 정보 조회 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping("")
    public ResponseEntity<? super GetSignInMemberResponseDto> getSignInUser(@AuthenticationPrincipal String email){
        return memberService.getSignInMember(email);
    }

    @Operation(summary = "회원 정보 email로 조회", description = "회원 정보 email로 조회 API",
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponseDto> getUserByEmail(
            @Parameter(name = "email", description = "email", in = ParameterIn.PATH) @PathVariable(value = "email") String email){
        return memberService.getUserByEmail(email);
    }

    @Operation(summary = "회원 닉네임 수정", description = "회원 닉네임 수정 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = { PatchNicknameRequestDto.class })
                    )

            ),
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(@RequestBody @Valid PatchNicknameRequestDto patchUserRequestDto, @AuthenticationPrincipal String email){
        return memberService.patchNickname(patchUserRequestDto, email);
    }

    @Operation(summary = "회원 프로필 이미지 수정", description = "회원 프로필 이미지 수정 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = { PatchProfileRequestDto.class })
                    )

            ),
            responses = {
                    @ApiResponse(responseCode = "SU", description = "Success."),
                    @ApiResponse(responseCode = "NM", description = "This user does not exist."),
                    @ApiResponse(responseCode = "DBE", description = "Database error")
            }
    )
    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(@RequestBody PatchProfileRequestDto patchProfileRequestDto, @AuthenticationPrincipal String email){
        return memberService.patchProfileImage(patchProfileRequestDto, email);
    }

}

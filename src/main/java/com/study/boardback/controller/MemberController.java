package com.study.boardback.controller;

import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.dto.response.member.GetUserResponseDto;
import com.study.boardback.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

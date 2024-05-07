package com.study.boardback.controller;

import com.study.boardback.dto.response.member.GetSignInMemberResponseDto;
import com.study.boardback.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "Member", description = "Member Rest API Controller")
public class MemberController {

    private final MemberService memberService;

//    @Operation(summary = "회원 정보 조회", description = "회원 정보 조회 API",
//            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
//                    content = @Content(
//                            mediaType = MediaType.APPLICATION_JSON_VALUE
//                    )
//            ),
//            responses = {
//                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = "application/json")),
//                    @ApiResponse(responseCode = "NM", description = "This user does not exist.", content = @Content(mediaType = "application/json")),
//                    @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = "application/json"))
//            }
//    )
    @GetMapping("")
    public ResponseEntity<? super GetSignInMemberResponseDto> getSignInUser(@AuthenticationPrincipal String email){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return memberService.getSignInMember(email);
    }


}

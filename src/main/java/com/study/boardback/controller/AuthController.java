package com.study.boardback.controller;

import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.auth.SignUpResponseDto;
import com.study.boardback.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    /**
     * SignUp
     * @param requestBody
     * @return ResponseEntity<? super SignUpResponseDto>
     */
    @Operation(summary = "회원가입", description = "회원가입 API")
    @Parameters({
            @Parameter(name = "email", description = "이메일", example = "email@email.com"),
            @Parameter(name = "password", description = "8자~20자 이내", example = "abcd1234"),
            @Parameter(name = "nickname", description = "별명", example = "별명"),
            @Parameter(name = "telNumber", description = "휴대번호", example = "010-1234-1234"),
            @Parameter(name = "address", description = "주소", example = "서울 서초구 서초동"),
            @Parameter(name = "addressDetail", description = "상세 주소", example = "좋은아파트 201동 202호"),
            @Parameter(name = "agreedPersonal", description = "개인정보 동의 여부", example = "true or false")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "DE", description = "Duplicate email.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "DN", description = "Duplicate nickname.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "DT", description = "Duplicate tel number.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody){
        return authService.signUp(requestBody);
    }
}

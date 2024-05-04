package com.study.boardback.controller;

import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.dto.response.auth.SignUpResponseDto;
import com.study.boardback.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    @Operation(summary = "회원가입", description = "회원가입 API",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = { SignUpRequestDto.class })
                    )
            ),
            responses ={
                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DE", description = "Duplicate email.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DN", description = "Duplicate nickname.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DT", description = "Duplicate tel number.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = "application/json"))
            }
    )
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestBody){
        return authService.signUp(requestBody);
    }
}


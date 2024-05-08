package com.study.boardback.controller;

import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "File", description = "File Controller")
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "이미지 업로드", description = "이미지 업로드",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(allOf = { SignUpRequestDto.class })
                    )
            ),
            responses ={
                    @ApiResponse(responseCode = "SU", description = "Success.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DE", description = "Duplicate email.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DN", description = "Duplicate nickname.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DT", description = "Duplicate tel number.", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "DBE", description = "Database error", content = @Content(mediaType = "application/json"))
            })
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return fileService.upload(file);
    }

    @Operation(summary = "이미지 불러오기", description = "이미지 조회")
    @GetMapping(value = "{fileName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    public Resource getImage(@PathVariable("fileName") String fileName){
       return fileService.getImage(fileName);
    }

}

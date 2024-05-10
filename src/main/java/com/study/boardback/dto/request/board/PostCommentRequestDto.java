package com.study.boardback.dto.request.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostCommentRequestDto {

    @NotBlank
    private String comment;

}

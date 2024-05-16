package com.study.boardback.dto.response.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.Image;
import com.study.boardback.repository.resultSet.GetBoardResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetBoardResponseDto extends ResponseDto {

    private int boardIdx;
    private String title;
    private String content;
    private List<String> boardImageList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDt;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    private GetBoardResponseDto(GetBoardResultSet resultSet, List<Image> imageEntities){
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.boardIdx = resultSet.getBoardIdx();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.regDt = resultSet.getRegDt();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();
        this.boardImageList = imageEntities.stream()
                .map(Image::getImage)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<Image> imageEntities){
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(result);
    }

}

package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.entity.ImageEntity;
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
    private LocalDateTime regDt;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities){
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.boardIdx = resultSet.getBoardIdx();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.regDt = resultSet.getRegDt();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();
        this.boardImageList = imageEntities.stream()
                .map(ImageEntity::getImage)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities){
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet, imageEntities);
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistBoard(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_BOARD);
    }

}
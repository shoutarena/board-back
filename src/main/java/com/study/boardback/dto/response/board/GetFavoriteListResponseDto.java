package com.study.boardback.dto.response.board;

import com.study.boardback.common.ResponseCode;
import com.study.boardback.dto.object.FavoriteList;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.repository.resultSet.GetFavoriteListResultSet;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {

    private List<FavoriteList> favoriteList;

    private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
        super(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        this.favoriteList = FavoriteList.copyList(resultSets);
    }

    public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets){
        return ResponseEntity.status(ResponseCode.SUCCESS.getHttpStatus()).body(new GetFavoriteListResponseDto(resultSets));
    }

    public static ResponseEntity<ResponseDto> noExistBoard(){
        return ResponseDto.getResponseEntityByResponseCode(ResponseCode.NOT_EXISTED_BOARD);
    }

}

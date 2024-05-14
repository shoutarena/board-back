package com.study.boardback.dto.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.boardback.entity.BoardListViewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardList {

    private int boardIdx;
    private String title;
    private String content;
    private String boardTitleImage;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDt;
    private String nickname;
    private String profileImage;
    private String email;

    public BoardList(BoardListViewEntity boardListViewEntity) {
        this.boardIdx = boardListViewEntity.getBoardIdx();
        this.title = boardListViewEntity.getTitle();
        this.content = boardListViewEntity.getContent();
        this.boardTitleImage = boardListViewEntity.getImage();
        this.favoriteCount = boardListViewEntity.getFavoriteCount();
        this.commentCount = boardListViewEntity.getCommentCount();
        this.viewCount = boardListViewEntity.getViewCount();
        this.regDt = boardListViewEntity.getRegDt();
        this.nickname = boardListViewEntity.getNickname();
        this.profileImage = boardListViewEntity.getProfileImage();
        this.email = boardListViewEntity.getEmail();
    }

    public static List<BoardList> getList(List<BoardListViewEntity> boardListViewEntities){
        return boardListViewEntities.stream().map(BoardList::new).collect(Collectors.toList());
    }

}

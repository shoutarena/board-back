package com.study.boardback.dto.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.boardback.entity.BoardListView;
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

    public BoardList(BoardListView boardListView) {
        this.boardIdx = boardListView.getBoardIdx();
        this.title = boardListView.getTitle();
        this.content = boardListView.getContent();
        this.boardTitleImage = boardListView.getImage();
        this.favoriteCount = boardListView.getFavoriteCount();
        this.commentCount = boardListView.getCommentCount();
        this.viewCount = boardListView.getViewCount();
        this.regDt = boardListView.getRegDt();
        this.nickname = boardListView.getNickname();
        this.profileImage = boardListView.getProfileImage();
        this.email = boardListView.getEmail();
    }

    public static List<BoardList> getList(List<BoardListView> boardListViewEntities){
        return boardListViewEntities.stream().map(BoardList::new).collect(Collectors.toList());
    }

}

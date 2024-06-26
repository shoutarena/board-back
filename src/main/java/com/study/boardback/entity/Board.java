package com.study.boardback.entity;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.entity.baseEntity.BaseDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
@Entity(name = "board")
public class Board extends BaseDateTime {
    @Id
    @Column(name = "board_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardIdx;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name = "favorite_count", nullable = false)
    private int favoriteCount;
    
    @Column(name = "comment_count", nullable = false)
    private int commentCount;
    
    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "reg_idx", nullable = false)
    private int regIdx;

    public Board(PostBoardRequestDto postBoardRequestDto, Member member){
        this.title = postBoardRequestDto.getTitle();
        this.content = postBoardRequestDto.getContent();
        this.favoriteCount = 0;
        this.viewCount = 0;
        this.commentCount = 0;
        this.regIdx = member.getMemberIdx();
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseFavoriteCount(){
        this.favoriteCount++;
    }

    public void decreaseFavoriteCount(){
        this.favoriteCount--;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }
    public void patchBoard(PostBoardRequestDto patchBoardRequestDto){
        this.title = patchBoardRequestDto.getTitle();
        this.content = patchBoardRequestDto.getContent();
    }

}

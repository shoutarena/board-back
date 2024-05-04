package com.study.boardback.entity;

import com.study.boardback.entity.baseEntity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
@Entity(name = "board")
public class BoardEntity extends BaseDateTimeEntity {
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

}

package com.study.boardback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
@EntityListeners(AuditingEntityListener.class)
public class BoardListView {

    @Id
    @Column(name = "board_idx", nullable = false)
    private int boardIdx;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "content", nullable = false)
    private String content;
    
    @Column(name = "image", nullable = true)
    private String image;
    
    @Column(name = "favorite_count", nullable = false)
    private int favoriteCount;
    
    @Column(name = "comment_count", nullable = false)
    private int commentCount;
    
    @Column(name = "view_count", nullable = false)
    private int viewCount;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;
    
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;
    
    @Column(name = "profile_image", nullable = true)
    private String profileImage;
    
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    
}

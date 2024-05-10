package com.study.boardback.entity;

import com.study.boardback.dto.request.board.PostCommentRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@Entity(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity {
    @Id
    @Column(name = "comment_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentIdx;
    
    @Column(name = "reg_idx", nullable = false)
    private int regIdx;
    
    @Column(name = "board_idx", nullable = false)
    private int boardIdx;
    
    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    public CommentEntity(int boardIdx, PostCommentRequestDto requestDto, Integer memberIdx){
        this.boardIdx = boardIdx;
        this.content = requestDto.getComment();
        this.regIdx = memberIdx;
    }

}

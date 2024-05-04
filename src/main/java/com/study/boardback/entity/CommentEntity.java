package com.study.boardback.entity;

import com.study.boardback.entity.baseEntity.BaseDateTimeEntity;
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
public class CommentEntity extends BaseDateTimeEntity {
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

}

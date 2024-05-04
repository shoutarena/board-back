package com.study.boardback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
@Entity(name = "image")
@EntityListeners(AuditingEntityListener.class)
public class ImageEntity {

    @Id
    @Column(name = "image_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageIdx;

    @Column(name = "board_idx", nullable = true)
    private int boardIdx;

    @Column(name = "image", nullable = false)
    private String image;

}
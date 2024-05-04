package com.study.boardback.entity.primaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntityPK implements Serializable {

    @Column(name = "reg_idx", nullable = false)
    private int regIdx;

    @Column(name = "board_idx", nullable = false)
    private int boardIdx;
}

package com.study.boardback.entity;

import com.study.boardback.entity.primaryKey.FavoriteEntityPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorite")
@Entity(name = "favorite")
@IdClass(FavoriteEntityPK.class)
@EntityListeners(AuditingEntityListener.class)
public class Favorite {

    @Id
    @Column(name = "reg_idx", nullable = false)
    private int regIdx;
    @Id
    @Column(name = "board_idx", nullable = false)
    private int boardIdx;

}

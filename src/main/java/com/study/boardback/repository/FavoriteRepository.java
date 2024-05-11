package com.study.boardback.repository;

import com.study.boardback.entity.FavoriteEntity;
import com.study.boardback.entity.primaryKey.FavoriteEntityPK;
import com.study.boardback.repository.resultSet.GetFavoriteListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteEntityPK> {

    FavoriteEntity findByBoardIdxAndRegIdx(Integer boardIdx, Integer regIdx);

    @Query(value =
            "SELECT " +
            "    m.email, " +
            "    m.nickname, " +
            "    m.profile_image AS profileImage " +
            "FROM favorite f " +
            "INNER JOIN member m ON f.reg_idx = m.member_idx " +
            "WHERE f.board_idx = ?1 ",
            nativeQuery = true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);
}

package com.study.boardback.repository;

import com.study.boardback.entity.BoardEntity;
import com.study.boardback.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    @Query(
            value = "SELECT " +
                    "    b.board_idx AS boardIdx, " +
                    "    b.title AS title, " +
                    "    b.content AS content, " +
                    "    b.reg_dt AS regDt, " +
                    "    m.email AS writerEmail, " +
                    "    m.nickname AS writerNickname, " +
                    "    m.profile_image as writerProfileImage " +
                    "FROM board b " +
                    "INNER JOIN member m ON b.reg_idx = m.member_idx " +
                    "WHERE b.board_idx = ?1 ",
            nativeQuery = true
    )
    GetBoardResultSet getBoard(Integer boardIdx);

    BoardEntity findByBoardIdx(Integer boardIdx);

    boolean existsByBoardIdx(Integer email);
}

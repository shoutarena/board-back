package com.study.boardback.repository;

import com.study.boardback.entity.Comment;
import com.study.boardback.repository.resultSet.GetCommentListResultSet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value =
                    "SELECT " +
                    "    m.nickname, " +
                    "    m.profile_image AS profileImage, " +
                    "    c.reg_dt AS regDt, " +
                    "    c.content " +
                    "FROM comment c " +
                    "INNER JOIN member m on c.reg_idx = m.member_idx " +
                    "WHERE c.board_idx = ?1 " +
                    "ORDER BY regDt DESC ",
            nativeQuery = true
    )
    List<GetCommentListResultSet> getCommentList(Integer boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);
}

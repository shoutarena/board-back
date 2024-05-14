package com.study.boardback.repository;

import com.study.boardback.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    List<BoardListViewEntity> findByOrderByRegDtDesc();

    List<BoardListViewEntity> findTop3ByRegDtGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescRegDtDesc(LocalDateTime regDt);

    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByRegDtDesc(String title, String content);

}

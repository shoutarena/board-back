package com.study.boardback.repository;

import com.study.boardback.entity.BoardListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListView, Integer> {

    List<BoardListView> findByOrderByRegDtDesc();

    List<BoardListView> findTop3ByRegDtGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescRegDtDesc(LocalDateTime regDt);

    List<BoardListView> findByTitleContainsOrContentContainsOrderByRegDtDesc(String title, String content);

    List<BoardListView> findByEmailOrderByRegDtDesc(String email);

}

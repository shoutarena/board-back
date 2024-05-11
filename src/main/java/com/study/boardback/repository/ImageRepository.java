package com.study.boardback.repository;

import com.study.boardback.entity.ImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    List<ImageEntity> findByBoardIdx(int boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);
}

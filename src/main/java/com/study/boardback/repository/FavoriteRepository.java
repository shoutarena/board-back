package com.study.boardback.repository;

import com.study.boardback.entity.FavoriteEntity;
import com.study.boardback.entity.primaryKey.FavoriteEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoriteEntityPK> {
}

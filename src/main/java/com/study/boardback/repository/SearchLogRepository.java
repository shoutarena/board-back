package com.study.boardback.repository;

import com.study.boardback.entity.SearchLogEntity;
import com.study.boardback.repository.resultSet.GetPopularListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {

    @Query(
            value = "" +
                    "SELECT " +
                    "    search_word AS searchWord, " +
                    "    count(search_word) AS count " +
                    "FROM search_log " +
                    "GROUP BY search_word " +
                    "order by count DESC " +
                    "LIMIT 15 ",
            nativeQuery = true
    )
    List<GetPopularListResultSet> findByPopularList();
}

package com.study.boardback.repository;

import com.study.boardback.entity.SearchLog;
import com.study.boardback.repository.custom.SearchLogCustom;
import com.study.boardback.repository.resultSet.GetPopularListResultSet;
import com.study.boardback.repository.resultSet.GetRelationListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLog, Integer>, SearchLogCustom {

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

    @Query(
            value = "" +
                    "SELECT " +
                    "    relation_word AS relationWord, " +
                    "    count(relation_word) AS count " +
                    "FROM search_log " +
                    "WHERE search_word = ?1 " +
                    "AND relation_word IS NOT NULL " +
                    "GROUP BY relation_word " +
                    "ORDER BY count DESC " +
                    "LIMIT 15 ",
            nativeQuery = true
    )
    List<GetRelationListResultSet> findByRelationList(String searchWord);
}

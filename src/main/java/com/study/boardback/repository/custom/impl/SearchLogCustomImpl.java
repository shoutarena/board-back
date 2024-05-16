package com.study.boardback.repository.custom.impl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.boardback.dto.projections.GetPopularListResultDto;
import com.study.boardback.repository.custom.SearchLogCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.study.boardback.entity.QSearchLog.searchLog;

@Repository
@RequiredArgsConstructor
public class SearchLogCustomImpl implements SearchLogCustom {

    private final JPAQueryFactory queryFactory;
    @Override
    public List<GetPopularListResultDto> findByPopularListCustom() {
        List<Tuple> tupleList = queryFactory
                .select(searchLog.searchWord, searchLog.count())
                .from(searchLog)
                .groupBy(searchLog.searchWord)
                .orderBy(searchLog.searchWord.count().desc())
                .limit(15)
                .fetch();

        return tupleList.stream()
                .map(tuple ->
                        GetPopularListResultDto.builder()
                        .searchWord(tuple.get(searchLog.searchWord))
                        .count(searchLog.count())
                        .build()
                )
                .collect(Collectors.toList());

    }
}

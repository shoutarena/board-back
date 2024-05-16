package com.study.boardback.dto.projections;

import com.querydsl.core.types.dsl.NumberExpression;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class GetPopularListResultDto {
    private String searchWord;
    private NumberExpression<Long> count;
}

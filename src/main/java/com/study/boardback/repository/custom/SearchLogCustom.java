package com.study.boardback.repository.custom;

import com.study.boardback.dto.projections.GetPopularListResultDto;

import java.util.List;

public interface SearchLogCustom {
    List<GetPopularListResultDto> findByPopularListCustom();
}

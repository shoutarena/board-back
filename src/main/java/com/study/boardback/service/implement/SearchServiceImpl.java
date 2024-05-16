package com.study.boardback.service.implement;

import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.search.GetPopularListResponseDto;
import com.study.boardback.dto.response.search.GetRelationListResponseDto;
import com.study.boardback.repository.SearchLogRepository;
import com.study.boardback.repository.resultSet.GetPopularListResultSet;
import com.study.boardback.repository.resultSet.GetRelationListResultSet;
import com.study.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> popularList = new ArrayList<>();
        try {
            popularList = searchLogRepository.findByPopularList();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetPopularListResponseDto.success(popularList);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {
        List<GetRelationListResultSet> relationListResultSets = new ArrayList<>();
        try {
            relationListResultSets = searchLogRepository.findByRelationList(searchWord);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRelationListResponseDto.success(relationListResultSets);
    }
}

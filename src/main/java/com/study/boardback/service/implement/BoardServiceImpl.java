package com.study.boardback.service.implement;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.request.board.PostCommentRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.board.*;
import com.study.boardback.entity.*;
import com.study.boardback.repository.*;
import com.study.boardback.repository.resultSet.GetBoardResultSet;
import com.study.boardback.repository.resultSet.GetCommentListResultSet;
import com.study.boardback.repository.resultSet.GetFavoriteListResultSet;
import com.study.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final BoardListViewRepository boardListViewRepository;
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto postBoardRequestDto, String email) {

        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();

            BoardEntity boardEntity = new BoardEntity(postBoardRequestDto, memberEntity);
            boardRepository.save(boardEntity);

            int boardIdx = boardEntity.getBoardIdx();
            List<String> boardImageList = postBoardRequestDto.getBoardImageList();
            if(!CollectionUtils.isEmpty(boardImageList)){
                List<ImageEntity> imageEntities = boardImageList.stream()
                        .map(img -> new ImageEntity(boardIdx, img))
                        .toList();

                imageRepository.saveAll(imageEntities);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        try {

            resultSet = boardRepository.getBoard(boardIdx);
            if(ObjectUtils.isEmpty(resultSet)) return ResponseDto.noExistBoard();
            imageEntities = imageRepository.findByBoardIdx(boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardIdx, String email) {

        try {

            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return PutFavoriteResponseDto.noExistMember();

            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(boardEntity)) return PutFavoriteResponseDto.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardIdxAndRegIdx(boardIdx, memberEntity.getMemberIdx());
            if(ObjectUtils.isEmpty(favoriteEntity)){
                favoriteEntity = new FavoriteEntity(memberEntity.getMemberIdx(), boardIdx);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardIdx) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try {

            boolean existedBoard = boardRepository.existsByBoardIdx(boardIdx);
            if(!existedBoard) return GetFavoriteListResponseDto.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardIdx, PostCommentRequestDto requestBody, String email) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(boardEntity)) return ResponseDto.noExistBoard();
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();

            CommentEntity commentEntity = new CommentEntity(boardIdx, requestBody, memberEntity.getMemberIdx());
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardIdx) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();
        try {
            boolean existedBoard = boardRepository.existsByBoardIdx(boardIdx);
            if(!existedBoard) return  ResponseDto.noExistBoard();
            resultSets = commentRepository.getCommentList(boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCommentListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardIdx) {

        try {
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(boardEntity)) return ResponseDto.noExistBoard();
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardIdx, String email) {

        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(boardEntity)) return ResponseDto.noExistBoard();
            boolean isWriter = boardEntity.getRegIdx() == memberEntity.getMemberIdx();
            if(!isWriter) return ResponseDto.noPermission();

            imageRepository.deleteByBoardIdx(boardIdx);
            commentRepository.deleteByBoardIdx(boardIdx);
            favoriteRepository.deleteByBoardIdx(boardIdx);
            boardRepository.delete(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PostBoardRequestDto requestBody, Integer boardIdx, String email) {

        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return ResponseDto.noExistMember();
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(boardEntity)) return ResponseDto.noExistBoard();
            boolean isWriter = boardEntity.getRegIdx() == memberEntity.getMemberIdx();
            if(!isWriter) return ResponseDto.noPermission();
            boardEntity.patchBoard(requestBody);
            boardRepository.save(boardEntity);

            imageRepository.deleteByBoardIdx(boardIdx);
            List<String> imageList = requestBody.getBoardImageList();
            if(!CollectionUtils.isEmpty(imageList)){
                List<ImageEntity> imageEntities = imageList.stream()
                        .map(image -> new ImageEntity(boardIdx, image))
                        .collect(Collectors.toList());
                imageRepository.saveAll(imageEntities);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            boardListViewEntities = boardListViewRepository.findByOrderByRegDtDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
        try {
            LocalDateTime ldt = LocalDateTime.now().minusDays(7);
            boardListViewEntities = boardListViewRepository.findTop3ByRegDtGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescRegDtDesc(ldt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByRegDtDesc(searchWord, searchWord);
            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLogEntity);
            boolean relation = preSearchWord != null;
            if(relation) {
                searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLogEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListViewEntities);
    }
}

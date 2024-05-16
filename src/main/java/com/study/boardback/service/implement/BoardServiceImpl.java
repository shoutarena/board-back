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
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();

            Board board = new Board(postBoardRequestDto, member);
            boardRepository.save(board);

            int boardIdx = board.getBoardIdx();
            List<String> boardImageList = postBoardRequestDto.getBoardImageList();
            if(!CollectionUtils.isEmpty(boardImageList)){
                List<Image> imageEntities = boardImageList.stream()
                        .map(img -> new Image(boardIdx, img))
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
        List<Image> imageEntities = new ArrayList<>();
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

            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return PutFavoriteResponseDto.noExistMember();

            Board board = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(board)) return PutFavoriteResponseDto.noExistBoard();

            Favorite favorite = favoriteRepository.findByBoardIdxAndRegIdx(boardIdx, member.getMemberIdx());
            if(ObjectUtils.isEmpty(favorite)){
                favorite = new Favorite(member.getMemberIdx(), boardIdx);
                favoriteRepository.save(favorite);
                board.increaseFavoriteCount();
            }else {
                favoriteRepository.delete(favorite);
                board.decreaseFavoriteCount();
            }
            boardRepository.save(board);

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
            Board board = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(board)) return ResponseDto.noExistBoard();
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();

            Comment comment = new Comment(boardIdx, requestBody, member.getMemberIdx());
            commentRepository.save(comment);

            board.increaseCommentCount();
            boardRepository.save(board);

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
            Board board = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(board)) return ResponseDto.noExistBoard();
            board.increaseViewCount();
            boardRepository.save(board);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardIdx, String email) {

        try {
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();
            Board board = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(board)) return ResponseDto.noExistBoard();
            boolean isWriter = board.getRegIdx() == member.getMemberIdx();
            if(!isWriter) return ResponseDto.noPermission();

            imageRepository.deleteByBoardIdx(boardIdx);
            commentRepository.deleteByBoardIdx(boardIdx);
            favoriteRepository.deleteByBoardIdx(boardIdx);
            boardRepository.delete(board);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PostBoardRequestDto requestBody, Integer boardIdx, String email) {

        try {
            Member member = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(member)) return ResponseDto.noExistMember();
            Board board = boardRepository.findByBoardIdx(boardIdx);
            if(ObjectUtils.isEmpty(board)) return ResponseDto.noExistBoard();
            boolean isWriter = board.getRegIdx() == member.getMemberIdx();
            if(!isWriter) return ResponseDto.noPermission();
            board.patchBoard(requestBody);
            boardRepository.save(board);

            imageRepository.deleteByBoardIdx(boardIdx);
            List<String> imageList = requestBody.getBoardImageList();
            if(!CollectionUtils.isEmpty(imageList)){
                List<Image> imageEntities = imageList.stream()
                        .map(image -> new Image(boardIdx, image))
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

        List<BoardListView> boardListViewEntities = new ArrayList<>();

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

        List<BoardListView> boardListViewEntities = new ArrayList<>();
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

        List<BoardListView> boardListViewEntities = new ArrayList<>();

        try {
            boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByRegDtDesc(searchWord, searchWord);
            SearchLog searchLog = new SearchLog(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLog);
            boolean relation = preSearchWord != null;
            if(relation) {
                searchLog = new SearchLog(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetMemberBoardListResponseDto> getUserBoardList(String email) {
        List<BoardListView> boardListViewEntities = new ArrayList<>();
        try {
            boardListViewEntities = boardListViewRepository.findByEmailOrderByRegDtDesc(email);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMemberBoardListResponseDto.success(boardListViewEntities);
    }
}

package com.study.boardback.service.implement;

import com.study.boardback.dto.request.board.PostBoardRequestDto;
import com.study.boardback.dto.response.ResponseDto;
import com.study.boardback.dto.response.board.GetBoardResponseDto;
import com.study.boardback.dto.response.board.GetFavoriteListResponseDto;
import com.study.boardback.dto.response.board.PostBoardResponseDto;
import com.study.boardback.dto.response.board.PutFavoriteResponseDto;
import com.study.boardback.entity.BoardEntity;
import com.study.boardback.entity.FavoriteEntity;
import com.study.boardback.entity.ImageEntity;
import com.study.boardback.entity.MemberEntity;
import com.study.boardback.repository.BoardRepository;
import com.study.boardback.repository.FavoriteRepository;
import com.study.boardback.repository.ImageRepository;
import com.study.boardback.repository.MemberRepository;
import com.study.boardback.repository.resultSet.GetBoardResultSet;
import com.study.boardback.repository.resultSet.GetFavoriteListResultSet;
import com.study.boardback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto postBoardRequestDto, String email) {

        try {
            MemberEntity memberEntity = memberRepository.findByEmail(email);
            if(ObjectUtils.isEmpty(memberEntity)) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(postBoardRequestDto, memberEntity);
            boardRepository.save(boardEntity);

            int boardIdx = boardEntity.getBoardIdx();
            List<String> boardImageList = postBoardRequestDto.getBoardImageList();
            List<ImageEntity> imageEntities = boardImageList.stream()
                    .map(img -> new ImageEntity(boardIdx, img))
                    .toList();

            imageRepository.saveAll(imageEntities);

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
            if(ObjectUtils.isEmpty(resultSet)) return GetBoardResponseDto.notExistBoard();
            imageEntities = imageRepository.findByBoardIdx(boardIdx);
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(int boardIdx, String email) {

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
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(int boardIdx) {

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
}

package com.study.boardback.repository.resultSet;

import java.time.LocalDateTime;

public interface GetBoardResultSet {
    Integer getBoardIdx();
    String getTitle();
    String getContent();
    LocalDateTime getRegDt();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();

}

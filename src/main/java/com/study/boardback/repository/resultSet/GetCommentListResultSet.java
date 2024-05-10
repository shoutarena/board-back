package com.study.boardback.repository.resultSet;

import java.time.LocalDateTime;

public interface GetCommentListResultSet {

    String getNickname();
    String getProfileImage();
    LocalDateTime getRegDt();
    String getContent();

}

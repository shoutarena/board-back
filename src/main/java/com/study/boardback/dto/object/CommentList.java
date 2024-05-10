package com.study.boardback.dto.object;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.boardback.repository.resultSet.GetCommentListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentList {

    private String nickname;
    private String profileImage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDt;
    private String content;

    public CommentList(GetCommentListResultSet resultSet){
        this.nickname = resultSet.getNickname();
        this.profileImage = resultSet.getProfileImage();
        this.regDt = resultSet.getRegDt();
        this.content = resultSet.getContent();
    }

    public static List<CommentList> copyList(List<GetCommentListResultSet> resultSets){
        return resultSets.stream()
                .map(CommentList::new)
                .collect(Collectors.toList());
    }

}

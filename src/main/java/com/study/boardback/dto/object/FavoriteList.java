package com.study.boardback.dto.object;

import com.study.boardback.repository.resultSet.GetFavoriteListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteList {

    private String email;
    private String nickname;
    private String profileImage;

    public FavoriteList(GetFavoriteListResultSet resultSet){
        this.email = resultSet.getEmail();
        this.nickname = resultSet.getNickname();
        this.profileImage = resultSet.getProfileImage();
    }

    public static List<FavoriteList> copyList(List<GetFavoriteListResultSet> resultSets){
        return resultSets.stream()
                .map(FavoriteList::new)
                .collect(Collectors.toList());
    }

}

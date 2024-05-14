package com.study.boardback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "search_log")
@Entity(name = "search_log")
@EntityListeners(AuditingEntityListener.class)
public class SearchLogEntity {

    @Id
    @Column(name = "search_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int searchIdx;
    
    @Column(name = "search_word", nullable = false)
    private String searchWord;
    
    @Column(name = "relation_word", nullable = true)
    private String relationWord;
    
    @Column(name = "relation", nullable = false)
    private boolean relation;

    public SearchLogEntity(String searchWord, String relationWord, boolean relation){
        this.searchWord = searchWord;
        this.relationWord = relationWord;
        this.relation = relation;
    }

}

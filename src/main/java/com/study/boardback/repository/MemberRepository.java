package com.study.boardback.repository;

import com.study.boardback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByTelNumber(String telNumber);
    Member findByEmail(String email);

}

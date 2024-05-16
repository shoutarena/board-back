package com.study.boardback.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.boardback.entity.MemberEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.study.boardback.entity.QMemberEntity.memberEntity;


@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory) {
        super(MemberEntity.class);
        this.queryFactory = queryFactory;
    }

    public MemberEntity findByEmail(String email){
        return queryFactory.selectFrom(memberEntity)
                .where(memberEntity.email.eq(email))
                .fetchOne();
    }

}

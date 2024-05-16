package com.study.boardback.entity;

import com.study.boardback.dto.request.auth.SignUpRequestDto;
import com.study.boardback.entity.baseEntity.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@Entity(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseDateTimeEntity {

    @Id
    @Column(name = "member_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberIdx;
    
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;
    
    @Column(name = "tel_number", nullable = false, length = 15)
    private String telNumber;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "address_detail", nullable = true)
    private String addressDetail;
    
    @Column(name = "profile_image", nullable = true)
    private String profileImage;

    @Column(name = "agreed_personal", nullable = false)
    private boolean agreedPersonal;

    public Member(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.agreedPersonal = dto.getAgreedPersonal();
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

}

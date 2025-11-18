package com.example.shop.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member") // 테이블 이름 지정
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login_id", length = 50, nullable = false, unique = true)
    private String loginId;

    @Column(name = "member_pw", length = 180, nullable = false)
    private String password;

    @Column(name = "member_phone")
    private String phoneNumber;

    @Column(name = "member_address")
    private String address;

    @Column(name = "member_point", nullable = false)
    private int point = 0;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    public void updateInfo(String password, String phoneNumber, String address) {
        if (password != null) this.password = password;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (address != null) this.address = address;
    }
}
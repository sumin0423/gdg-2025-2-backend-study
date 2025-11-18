package com.example.shop.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    private String loginId;      // 로그인 ID
    private String password;     // 비밀번호
    private String phoneNumber;  // 전화번호
    private String address;      // 주소
}

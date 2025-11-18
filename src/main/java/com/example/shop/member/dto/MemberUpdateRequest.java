package com.example.shop.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {

    private String password;     // 새 비밀번호
    private String phoneNumber;  // 새 전화번호
    private String address;      // 새 주소
}

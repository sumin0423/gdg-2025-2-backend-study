package com.example.shop.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 정보 수정 요청 DTO
 * - 비밀번호, 전화번호, 주소만 변경 가능
 */
@Getter
@NoArgsConstructor
public class MemberUpdateRequest {

    private String password;     // 새 비밀번호
    private String phoneNumber;  // 새 전화번호
    private String address;      // 새 주소
}

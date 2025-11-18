package com.example.shop.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 생성 요청 DTO
 * - 클라이언트에서 회원가입 시 전송하는 데이터 구조
 */
@Getter
@NoArgsConstructor
public class MemberCreateRequest {

    private String loginId;      // 로그인 ID
    private String password;     // 비밀번호
    private String phoneNumber;  // 전화번호
    private String address;      // 주소
}

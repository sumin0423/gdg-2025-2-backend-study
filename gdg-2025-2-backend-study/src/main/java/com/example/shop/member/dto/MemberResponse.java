package com.example.shop.member.dto;

import com.example.shop.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 응답 DTO
 * - API 응답 시 비밀번호 제외하고 클라이언트로 전달할 정보만 포함
 */
@Getter
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String loginId;
    private String phoneNumber;
    private String address;
    private int point;

    // Member 엔티티 → DTO 변환 메서드
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getLoginId(),
                member.getPhoneNumber(),
                member.getAddress(),
                member.getPoint()
        );
    }
}

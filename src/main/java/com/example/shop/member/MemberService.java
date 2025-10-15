package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberResponse;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    public Long createMember(MemberCreateRequest req) {
        System.out.println("회원 생성 요청: " + req.getLoginId());
        return 1L; // 임시 ID
    }

    public List<MemberResponse> getAllMembers() {
        return List.of(new MemberResponse(1L, "hong", "010-1234-5678", "서울 마포구", 0));
    }

    public MemberResponse getMemberById(Long id) {
        return new MemberResponse(id, "hong", "010-1234-5678", "서울 마포구", 0);
    }

    public void updateMember(Long id, MemberUpdateRequest req) {
        System.out.println("회원 " + id + " 수정 요청");
    }

    public void deleteMember(Long id) {
        System.out.println("회원 " + id + " 삭제 요청");
    }
}
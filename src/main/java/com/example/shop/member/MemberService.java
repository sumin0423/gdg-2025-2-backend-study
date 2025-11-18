package com.example.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member register(String loginId, String password, String phoneNumber, String address) {
        // 중복 로그인 ID 체크
        Member existing = memberRepository.findByLoginId(loginId);
        if (existing != null) {
            throw new IllegalStateException("이미 존재하는 로그인 ID입니다.");
        }

        Member member = new Member(loginId, password, phoneNumber, address);
        memberRepository.save(member);
        return member;
    }

    public Member get(Long id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다. id=" + id);
        }
        return member;
    }

    public List<Member> list() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member update(Long id, String password, String phoneNumber, String address) {
        Member member = get(id); // 존재 여부 체크 포함
        member.updateInfo(password, phoneNumber, address);
        memberRepository.save(member); // 메모리 저장소에서는 다시 put
        return member;
    }

    @Transactional
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
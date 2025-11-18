package com.example.shop.member;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberResponse;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberResponse create(@RequestBody MemberCreateRequest req) {
        Member member = memberService.register(
                req.getLoginId(),
                req.getPassword(),
                req.getPhoneNumber(),
                req.getAddress()
        );
        return MemberResponse.from(member);
    }

    @GetMapping("/{id}")
    public MemberResponse get(@PathVariable Long id) {
        return MemberResponse.from(memberService.get(id));
    }

    @GetMapping
    public List<MemberResponse> list() {
        return memberService.list().stream()
                .map(MemberResponse::from)
                .toList();
    }

    @PutMapping("/{id}")
    public MemberResponse update(@PathVariable Long id,
                                 @RequestBody MemberUpdateRequest req) {
        Member member = memberService.update(
                id,
                req.getPassword(),
                req.getPhoneNumber(),
                req.getAddress()
        );
        return MemberResponse.from(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
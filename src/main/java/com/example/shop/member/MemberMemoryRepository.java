package com.example.shop.member;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberMemoryRepository implements MemberRepository {

    private final Map<Long, Member> store = new HashMap<>();
    private long sequence = 0L;

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findByLoginId(String loginId) {
        for (Member member : store.values()) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public void save(Member member) {
        // Member 엔티티에 setter가 없어서 리플렉션으로 id 세팅
        if (member.getId() == null) {
            try {
                Field idField = Member.class.getDeclaredField("id");
                idField.setAccessible(true);
                sequence++;
                idField.set(member, sequence);
            } catch (Exception e) {
                throw new IllegalStateException("메모리 저장소에서 ID 설정 실패", e);
            }
        }
        store.put(member.getId(), member);
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
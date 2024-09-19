package com.example.hello_spring.repository;


import com.example.hello_spring.domain.Member;
import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    // 저장 변수 선언
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    // MemberRepository 메소드 작성
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(m -> m.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearData() {
        store.clear();
    }

}

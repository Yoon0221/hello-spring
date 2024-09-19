package com.example.hello_spring.repository;


import com.example.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


class MemoryMemberRepositoryTest {

    // 리포지토리 기능 사용을 위한 의존성주입
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();


    // 기능 테스트
    @Test
    void save() {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setName("string1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("string2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("string2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setName("string1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("string2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @AfterEach // 모든 Test 이후에 디비 비우기
    void clearData() {
        memberRepository.clearData();
    }
}

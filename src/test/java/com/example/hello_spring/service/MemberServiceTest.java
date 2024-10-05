package com.example.hello_spring.service;


import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 모든 Test 이후에 디비 비우기
    @Test
    @AfterEach
    void clearData() {
        memberRepository.clearData();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("나도");

        // when
        Long join_memberId = memberService.join(member);

        // then
        Member find_member = memberService.findOne(join_memberId).get();
        assertThat(member.getName()).isEqualTo(find_member.getName());
    }

    @Test
    void 중복_이름_확인() {
        // given
        Member member1 = new Member();
        member1.setName("나도");
        Member member2 = new Member();
        member2.setName("나도");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임입니다.");
    }

    @Test
    void 회원_조회() {
    }

    @Test
    void 개별_회원_조회() {
    }

}
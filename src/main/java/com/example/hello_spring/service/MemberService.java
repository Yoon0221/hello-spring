package com.example.hello_spring.service;


import com.example.hello_spring.domain.Member;
import com.example.hello_spring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 1. 회원가입
    public Long join(Member member) {

        vaildName(member); // 중복 이름 확인

        memberRepository.save(member);
        return member.getId();
    }

    // 2. 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 3. 개인 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


    private void vaildName(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });
    }

}

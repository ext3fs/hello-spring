package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 같은 이름의 회원이 있는지 체크
   * @param member - member obj
   */
  private void validateDuplicateMember(Member member) {
    memberRepository
        .findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 이름입니다");
        });
  }

  /**
   * 회원가입
   * @param member - Member obj
   * @return member id
   */
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  /**
   * 전체 회원의 리스트
   * @return List<Member>
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 회원 찾기
   * @param memberId - 회원 아이디
   * @return Optional<Member>
   */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }

}

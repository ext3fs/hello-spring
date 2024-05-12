package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

  @Autowired MemberRepository memberRepository;
  @Autowired MemberService memberService;


  @Test
  void join() {
    //given
    Member member = new Member();
    member.setName("young");

    //when
    final Long joinId = memberService.join(member);

    //then
    final Member joinMember = memberService.findOne(joinId).orElse(null);
    assertThat(member.getName()).isEqualTo(joinMember.getName());
  }

  @Test
  void joinException() {
    //given
    Member member1 = new Member();
    member1.setName("young");

    Member member2 = new Member();
    member2.setName("young");

    //when
    memberService.join(member1);
    assertThatThrownBy(() -> memberService.join(member2))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("이미 존재하는 이름입니다");
  }

}
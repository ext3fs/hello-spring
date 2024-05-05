package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {

  MemoryMemberRepository memberRepository;
  MemberService memberService;

  @BeforeEach
  void beforeAll() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void join() {
    //given
    Member member = new Member();
    member.setName("young");

    //when
    final Long joinId = memberService.join(member);

    //then
    final Member joinMember = memberService.findOne(joinId).orElse(null);
    assertThat(member).isEqualTo(joinMember);
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

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}
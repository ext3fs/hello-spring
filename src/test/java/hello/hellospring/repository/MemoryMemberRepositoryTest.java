package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  @AfterEach
  void afterEach() {
    repository.clearStore();
  }

  @Test
  void save() {
    Member member = new Member();
    member.setName("spring");
    repository.save(member);
    Member result = repository.findById(member.getId()).orElse(null);
//    System.out.println("result = " + (result == member));
//    Assertions.assertEquals(member, result);
    Assertions.assertThat(member).isEqualTo(result);
  }

  @Test
  void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring2").orElse(null);
    Assertions.assertThat(result).isEqualTo(member2);
  }

  @Test
  void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member member3 = new Member();
    member3.setName("spring3");
    repository.save(member3);

    final List<Member> result = repository.findAll();
    Assertions.assertThat(result.size()).isEqualTo(3);
  }

}

package japbook.jpashop.repository;

import japbook.jpashop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional /* JPA는 Transaction을 기반으로 작동(필수적용) */
    @Rollback(false) /* 테스트 환경에서는 트랜젝션 후 록백이 default로 이루어짐 */
    public void testMember() throws Exception {
        Member member = new Member();
        member.setMemberName("user1");

        Long id = memberRepository.save(member);
        Member selectMember = memberRepository.find(id);

    }

}
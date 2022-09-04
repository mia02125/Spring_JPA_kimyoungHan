package japbook.jpashop.repository;

import japbook.jpashop.vo.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional /* JPA는 Transaction을 기반으로 작동 */
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Transactional
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

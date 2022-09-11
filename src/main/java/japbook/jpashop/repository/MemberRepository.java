package japbook.jpashop.repository;

import japbook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }
    public Member findMember(Long id) {
        return em.find(Member.class, id);
    }
    public Optional<List<Member>> findMembers() {
        /* qlString : Entity객체를 조회하여 sql을 만듬 */
        return Optional.ofNullable(em.createQuery("SELECT m FROM Member m", Member.class)
            .getResultList());
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

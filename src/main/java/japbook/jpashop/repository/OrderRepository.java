package japbook.jpashop.repository;

import japbook.jpashop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Order find(Long id) {
        return em.find(Order.class, id);
    }

}

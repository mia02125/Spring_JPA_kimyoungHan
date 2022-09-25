package japbook.jpashop.repository;

import japbook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {


    private final EntityManager em;


    public void save(Order order) {
        em.persist(order);
    }
    public Optional<List<Order>> findOrders() {
        return Optional.ofNullable(em.createQuery("SELECT o FROM Order o", Order.class)
                .getResultList());
    }
    public Order findOrder(Long id) {
        return em.find(Order.class, id);
    }
}

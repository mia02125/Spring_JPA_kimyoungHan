package japbook.jpashop.repository;

import japbook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null) {
            em.persist(item); // insert
        } else em.merge(item); // update
    }

    public Item findItem(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findItems() {
        return em.createQuery("SELECT i FROM Item i", Item.class)
                .getResultList();
    }
}

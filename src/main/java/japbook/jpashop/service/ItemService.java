package japbook.jpashop.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import japbook.jpashop.config.QuerydslConfig;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return itemRepository.findItems();
    }

    @Transactional(readOnly = true)
    public Item findItem(Long itemId) {
        return itemRepository.findItem(itemId);
    }
}

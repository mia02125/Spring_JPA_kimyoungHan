package japbook.jpashop.service;

import japbook.jpashop.domain.Address;
import japbook.jpashop.domain.Member;
import japbook.jpashop.domain.item.Book;
import japbook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {


    @Autowired
    EntityManager em;
    @Autowired
    private OrderService orderService;

    @Test
    public void 상품주문() throws Exception {
        Member member = new Member();
        member.setName("회원 1 ");
        member.setHomeAddress(new Address("서울", "숭인로", "50"));
        em.persist(member);

        Book book = new Book();
        book.setName("JAP");
        book.setPrice(1000);
        book.setStockQuantity(10);

        orderService.order(member.getId(), book.getId(), 5);

    }

    @Test
    public void 주문취소() throws Exception {

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {

    }

}
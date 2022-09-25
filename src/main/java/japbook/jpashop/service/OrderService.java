package japbook.jpashop.service;

import japbook.jpashop.domain.Delivery;
import japbook.jpashop.domain.Member;
import japbook.jpashop.domain.Order;
import japbook.jpashop.domain.OrderItem;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.repository.ItemRepository;
import japbook.jpashop.repository.MemberRepository;
import japbook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 상품 주문
     */
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findMember(memberId);
        Item item = itemRepository.findItem(itemId);
        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getHomeAddress());
        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, count);
        Order.createOrder(member, delivery, orderItem);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getOrderId();
    }
    /**
     * 상품 취소
     */
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOrder(orderId);
        order.cancel();

    }

    /**
     * 검색
     */
    public Optional<List<Order>> findOrders() {
        Optional<List<Order>> orders = orderRepository.findOrders();
        return orders;
    }
}

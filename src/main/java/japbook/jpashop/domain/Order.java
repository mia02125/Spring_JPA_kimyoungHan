package japbook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;

    /*
    * 연관관계의 주체는 FK에 가까운 Entity을 기준으로 둔다.
    */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    /*
    상황마다 다른데 보통 access하는 기준으로 연관관계의 주체로 잡음.
    */
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; /* 주문 시간 */

    /*
    ORDINAL : 숫자로 지정 (절대 사용 금지 => Enum 가운데 구분값을 추가하면 시스템 전체적인 문제 발생
    STRING : 문자열로 지정
    */
    @Enumerated(EnumType.STRING)
    private OrderStatus status; /* 주문 상태 [ ORDER, CANCEL ]*/
}

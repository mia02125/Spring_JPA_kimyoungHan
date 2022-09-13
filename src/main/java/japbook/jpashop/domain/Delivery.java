package japbook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;
    /*
    ORDINAL : 숫자로 지정
    * 절대 사용 금지 => Enum 가운데 구분값을 추가하면 시스템 전체적인 문제 발생
    STRING : 문자열로 지정
    */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    /*
    연관관계 편의 메서드
    */
    public void setOrder(Order order) {
        this.order = order;
        order.setDelivery(this);
    }
}

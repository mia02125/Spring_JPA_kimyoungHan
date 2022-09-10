package japbook.jpashop.domain.item;


import japbook.jpashop.domain.Category;
import japbook.jpashop.domain.CategoryItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
/*
* 상속 매핑
1. SINGLE_TABLE : 테이블을 하나만 사용하는 방식(조인이 필요없기 때문에 조회 성능이 빠름)
2. JOINED : 조인 전략(쿼리 복잡 및 CRUD할 경우 부모/자식 클래스 모두 저장하기 때문에 두번의 쿼리 실행)
3. TABLE_PER_CLASS : 각각의 테이블을 만드는 방식 (추천하지않음 -> union을 사용하기 때문에 성능이 느림)
출처 : https://hyeooona825.tistory.com/90
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype") /* 부모클래스에 구분 컬럼 지정  */
@Setter
@Getter
/* 추상클래스로 생성 : 구현체를 가지고 할거임 */
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;


    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems;

    private int price;

    private int stockQuantity;

    /*
    연관관계 편의 메서드
    */
    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
        categoryItem.setItem(this);
    }
}

package japbook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
/*
Entity 저장 시 구분 컬럼에 입력할 값을 지정
ex) 'B'라 지정하면 Entity 저장 시 부모클래스인 Book의 dtype에 B가 저장
*/
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item {

    private String author;

    private String isbn;
}









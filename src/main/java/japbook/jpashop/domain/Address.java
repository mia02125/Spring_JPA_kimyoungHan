package japbook.jpashop.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Getter
@Embeddable /* JPA Enity 내 하나의 객체로 사용하고싶다면 해당 어노테이션 사용  */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {


    private String city;
    private String street;
    private String zipcode;

}

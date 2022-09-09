package japbook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable /* JPA Enity 내 하나의 객체로 사용하고싶다면 해당 어노테이션 사용  */
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

}

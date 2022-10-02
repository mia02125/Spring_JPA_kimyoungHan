package japbook.jpashop.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "회원 도메인")
@Entity
@Getter /* 실무에서는, 가급적 열어두기  */
@Setter /* 실무에서는, 꼭 필요한 경우에만 사용 */
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name")
    private String name;

    @Embedded /* 내장 타입을 포함 */
    @AttributeOverrides({ /*  homeAddress, workAddress의 컬럼명이 서로 중복되기 때문 */
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "street", column = @Column(name = "home_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))
    })
    private Address homeAddress;

    @Embedded /* 내장 타입을 포함 */
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "street", column = @Column(name = "company_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "company_zipcode"))
    })
    private Address companyAddress;
    /*
    * 연관관계의 주체는 FK에 가까운 Entity인 Order테이블을 기준으로
    * 두기때문에 mappedBy 사용하여 order에 직접적인 영향을 주지않고 읽기 전용으로 한다.
    * ex) member정보를 update할 경우 order테이블이 update되는 경우가 생기는 오류 발생
    * ( mappedBy = [Order테이블에 존재하는 Member의 변수명] )
    */
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}

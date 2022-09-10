package japbook.jpashop.controller;

import japbook.jpashop.domain.Order;
import japbook.jpashop.repository.MemberRepository;
import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("data", "hellow");
        return "index";
    }


    @PostMapping("/save")
    public void save() {
        Member member = new Member();
        Order order = new Order();
        member.setName("user1");
        Long id = memberRepository.save(member);
        Member selectMember = memberRepository.find(id);
        order.setMember(selectMember);
        memberRepository.save(member);
    }

    @ResponseBody
    @PostMapping("/find")
    public Order find(@RequestParam(name = "id") Long id) {
        Order selectOrder = orderRepository.find(id);
        return selectOrder;
    }


}

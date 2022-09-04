package japbook.jpashop.controller;

import japbook.jpashop.repository.MemberRepository;
import japbook.jpashop.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("")
    public String main(Model model) {

        Member member = new Member();
        member.setUsername("user1");

        Long id = memberRepository.save(member);
        Member selectMember = memberRepository.find(id);
        /* 검증(Assertions) */
        System.out.println(selectMember);
        model.addAttribute("data", "hellow");

        return "index";

    }
}

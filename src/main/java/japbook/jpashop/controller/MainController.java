package japbook.jpashop.controller;

import japbook.jpashop.domain.Order;
import japbook.jpashop.repository.MemberRepository;
import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("data", "hellow");
        return "index";
    }





}

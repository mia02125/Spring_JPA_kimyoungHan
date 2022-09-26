package japbook.jpashop.controller;

import japbook.jpashop.domain.Order;
import japbook.jpashop.repository.MemberRepository;
import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j /* 로그 */
public class MainController {

    @GetMapping("/")
    public String main() {
        log.info("home controller");
        return "home";
    }
}

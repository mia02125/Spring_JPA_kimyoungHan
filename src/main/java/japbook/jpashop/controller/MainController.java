package japbook.jpashop.controller;
import japbook.jpashop.domain.Member;
import japbook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j /* 로그 */
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public String main() {
        log.info("home controller");
        return "home";
    }

    @GetMapping("/member/signup")
    public String signupMember(Model model) {
        return "member/memberForm";
    }

    @GetMapping("/member/list")
    public ModelAndView memberList(ModelAndView mav) {
        Optional<List<Member>> members = memberService.findMembers();
        mav.addObject("members", members.orElseThrow());
        mav.setViewName("member/memberList");
        return mav;
    }
}

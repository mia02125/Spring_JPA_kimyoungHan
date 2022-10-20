package japbook.jpashop.controller;
import japbook.jpashop.domain.Member;
import japbook.jpashop.domain.item.Item;
import japbook.jpashop.service.ItemService;
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
    private final ItemService itemService;

    @GetMapping("/")
    public String main() {
        return "home";
    }

    @GetMapping("/member/signup")
    public String signupMember(Model model) {
        return "member/memberForm";
    }

    @GetMapping("/member/list")
    public ModelAndView memberList(ModelAndView mav) {
        Optional<List<Member>> members = Optional.ofNullable(memberService.findMembers());
        mav.addObject("members", members.orElseThrow());
        mav.setViewName("member/memberList");
        return mav;
    }

    @GetMapping("/items")
    public ModelAndView itemList(ModelAndView mav) {
        Optional<List<Item>> items = Optional.ofNullable(itemService.findItems());
        mav.addObject("items", items.orElseThrow());
        mav.setViewName("item/itemList");
        return mav;
    }
}

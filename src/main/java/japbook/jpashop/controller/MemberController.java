package japbook.jpashop.controller;

import japbook.jpashop.domain.Member;
import japbook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {

    private final MemberService memberService;

    /**
     *
     * @param member
     * @return
     */
    @ResponseBody
    @PostMapping("/member")
    public ResponseEntity<Long> join(@RequestBody Member member) {
        Long id = memberService.joinMember(member);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}

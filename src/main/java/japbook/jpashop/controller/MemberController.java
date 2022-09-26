package japbook.jpashop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api("회원")
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
    @ApiOperation(value = "회원 가입")
    @ResponseBody
    @PostMapping("/member")
    public ResponseEntity<Long> join(@RequestBody Member member) {
        Long id = joinMemberFn(member);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
    // 함수화 : ctrl + Alt + M
    private Long joinMemberFn(Member member) {
        Long id = memberService.joinMember(member);
        return id;
    }



    /*public ResponseEntity<Member> */


}

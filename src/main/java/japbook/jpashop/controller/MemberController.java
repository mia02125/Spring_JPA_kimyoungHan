package japbook.jpashop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import japbook.jpashop.domain.Member;
import japbook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.net.URISyntaxException;

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
    public ResponseEntity<Long> join(@RequestBody Member member, /* 실무에서는 Form DTO를 따로 만들어야 함.*/
                                     BindingResult bindingResult) {

        HttpHeaders httpHeaders = new HttpHeaders();


        if(bindingResult.hasErrors()) {
            try {
                URI redirectURI = new URI("member/memberForm");
                httpHeaders.setLocation(redirectURI);
            } catch(URISyntaxException e) {
                System.out.println(e.getMessage());
            }
        }

        Long id = joinMemberFn(member);
        return new ResponseEntity<>(id, httpHeaders, HttpStatus.CREATED);
    }
    // 함수화 : ctrl + Alt + M
    private Long joinMemberFn(Member member) {
        Long id = memberService.joinMember(member);
        return id;
    }



    /*public ResponseEntity<Member> */


}

package japbook.jpashop.service;

import japbook.jpashop.domain.Member;
import japbook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional /* 스프링 부트에서 지원하는 어노테이블 사용 */
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @return
     */
    public Long joinMember(Member member) {
        boolean valid = validateDuplicateMember(member);
        if(valid) {
            memberRepository.save(member);
        }
        return member.getId();
    }
    /**
     * 회원 전체 조회
     */
    @Transactional(readOnly = true) /* readOnly = true인 경우 성능 확보 */
    public List<Member> findMembers() {
        return memberRepository.findMembers();
    }

    @Transactional(readOnly = true) /* readOnly = true인 경우 성능 확보 */
    public Member findMember(Long id) {
        return memberRepository.findMember(id);
    }

    /* 중복회원 검증 */
    private boolean validateDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        return true;
    }
}

package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jpabook.jpashop.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입")
    public void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("Kim");

        // when
        Long saveId = memberService.join(member);

        // then
        assertThat(memberService.findOne(saveId)).isEqualTo(member);
    }

    @Test
    @DisplayName("중복 회원 가입 예외")
    public void duplicateMemberJoin() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when, then
        memberService.join(member1);
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }

}

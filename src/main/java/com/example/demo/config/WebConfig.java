package com.example.demo.config;

import com.example.demo.entity.Member;
import com.example.demo.notice.repository.member.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
/**
 * class          : WebConfig
 * author         : 오동준
 * date           : 2023/04/25
 * description    : 로그인 인터셉터
 */

/**
 * WebMvcConfigurer는 스프링 MVC에서 제공하는 인터페이스로, 웹 애플리케이션의 설정을 구성하는 데 사용됩니다. WebMvcConfigurer를 구현하면 스프링 MVC의 설정을 커스터마이징할 수 있습니다.
 * <p>
 * -   WebMvcConfigurer에서는 다음과 같은 메소드들을 제공합니다. -
 * <p>
 * addResourceHandlers              : 정적 리소스의 URL 매핑을 등록하는 메소드입니다.
 * addViewControllers               : 뷰 컨트롤러를 등록하는 메소드입니다.
 * configureDefaultServletHandling  : 기본 서블릿 설정을 구성하는 메소드입니다.
 * addInterceptors                  : 인터셉터를 등록하는 메소드입니다.
 * addArgumentResolvers             : 컨트롤러 메소드의 인자를 해결하는 리졸버를 등록하는 메소드입니다.
 * addReturnValueHandlers           : 컨트롤러 메소드의 반환 값을 처리하는 핸들러를 등록하는 메소드입니다.
 * addPathPatterns : 인터셉터를 적용할 URL 패턴을 지정합니다.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private MemberService memberService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(memberService))
                .addPathPatterns("/blood/**", "/board/**");
    }

    /**
     * HandlerInterceptorAdapter 는 스프링 프레임 워크에서 제공하는  HandlerInterceptor 인터페이스의 기본 구현체 중 하나입니다 .
     * <p>
     * 스프링 MVC의 인터셉터 기능을 구현할 때 사용되는 인터페이스입니다.
     * <p>
     * HandlerInterceptorAdapter 는 HandlerInterceptor 인터페이스를 구현하고 있으며, HandlerInterceptor 인터페이스의 메서드를 오버라이드하여 구현합니다.
     * <p>
     * 메소드 설명
     * preHandle        : 핸들러 메소드가 실행되기 전에 호출되는 메소드입니다. 핸들러 메소드의 실행 여부를 결정하는 boolean 값을 반환합니다. 만약 false를 반환하면, 핸들러 메소드가 실행되지 않습니다.
     * postHandle       : 핸들러 메소드가 실행된 후, 화면이 렌더링 되기 전에 호출되는 메소드입니다.
     * afterCompletion  : 핸들러 메소드가 실행된 후, 화면이 렌더링 된 후에 호출되는 메소드입니다.
     */
    public class UserInterceptor extends HandlerInterceptorAdapter {
        private MemberService memberService;

        private MemberRepository memberRepository;

        public UserInterceptor(MemberService memberService) {
            this.memberService = memberService;
        }


        /**
         * preHandle        : 핸들러 메소드가 실행되기 전에 호출되는 메소드입니다. 핸들러 메소드의 실행 여부를 결정하는 boolean 값을 반환합니다. 만약 false를 반환하면, 핸들러 메소드가 실행되지 않습니다.
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession(false);
//            Member member = (Member) session.getAttribute("user");

            if (session != null) {
                Member member = (Member) session.getAttribute("user");
                if (member != null) {
                    request.setAttribute("user", member);
                }
            } else {
                if (session == null)
                    response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                String msg = "로그인이 필요합니다.";
                String url = "/member/login";
                out.println("<script>location.href='/common/modal?msg=" + msg + ".&url= " + url + "';</script>");
                return false;
            }

            return true;

        }

    }
}

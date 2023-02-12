package springSecurity.springSecurity.view.servlet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //헤더에서 jwt token 받음
        log.info("---------------------------");
        log.info("jwt token do Filter");
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 유효성 체크
        if(!ObjectUtils.isEmpty(token) && jwtTokenProvider.validateToken(token)){
            log.info("token not null");
            // 토큰이 유효하면 토큰으로부터 유저 정보 받아옴
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            // securityContext에 Authentication 객체를 저장함
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }
}

package com.fs.projectboard.config;

import com.fs.projectboard.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing // JPA Auditing 활성화
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext()) // SecurityContext를 이용하여 현재 사용자를 알아낸다.
                .map(SecurityContext::getAuthentication) // SecurityContext에서 Authentication 객체를 꺼낸다.
                .filter(Authentication::isAuthenticated) // 인증된 사용자인지 확인한다.
                .map(Authentication::getPrincipal) // 인증된 사용자의 정보를 꺼낸다.
                .map(BoardPrincipal.class::cast) // BoardPrincipal로 캐스팅한다.
                .map(BoardPrincipal::getUsername); // 사용자의 아이디를 꺼낸다.
    }
}

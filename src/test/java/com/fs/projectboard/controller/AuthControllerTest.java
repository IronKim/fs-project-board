package com.fs.projectboard.controller;

import com.fs.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class) // @Import는 테스트에 필요한 설정 클래스를 임포트한다.
@WebMvcTest
public class AuthControllerTest {

    private final MockMvc mvc; // MockMvc는 스프링 MVC 테스트를 위한 클래스이다.

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 로그인 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenTryingToLogIn_thenReturnsLogInView() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/login")) // GET /login 요청을 보낸다.
                .andExpect(status().isOk()) // 응답의 상태코드가 200인지 검증한다.
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)); // 응답의 Content-Type이 text/html인지 검증한다.
    }
}

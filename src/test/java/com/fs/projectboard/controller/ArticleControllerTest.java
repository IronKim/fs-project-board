package com.fs.projectboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class) // @WebMvcTest는 스프링 MVC 테스트를 위한 어노테이션이다.
class ArticleControllerTest {

    private final MockMvc mvc; // MockMvc는 스프링 MVC 테스트를 위한 클래스이다.

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/articles")) // GET /articles 요청을 보낸다.
                .andExpect(status().isOk()) // 응답의 상태코드가 200인지 검증한다.
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 응답의 Content-Type이 text/html인지 검증한다.
                .andExpect(view().name("articles/index")) // 뷰의 이름이 articles/index인지 검증한다.
                .andExpect(model().attributeExists("articles")); // articles 속성이 존재하는지 검증한다.
    }

    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/articles/1")) // GET /articles/1 요청을 보낸다.
                .andExpect(status().isOk()) // 응답의 상태코드가 200인지 검증한다.
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 응답의 Content-Type이 text/html인지 검증한다.
                .andExpect(view().name("articles/detail")) // 뷰의 이름이 articles/detail인지 검증한다.
                .andExpect(model().attributeExists("article")) // article 속성이 존재하는지 검증한다.
                .andExpect(model().attributeExists("articleComments")); // comments 속성이 존재하는지 검증한다.
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/articles/search")) // GET /articles/search 요청을 보낸다.
                .andExpect(status().isOk()) // 응답의 상태코드가 200인지 검증한다.
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 응답의 Content-Type이 text/html인지 검증한다.
                .andExpect(view().name("articles/search")); // 뷰의 이름이 articles/search인지 검증한다.
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleHashtagView_thenReturnsArticleHashtagView() throws Exception {
        //Given

        //When & Then
        mvc.perform(get("/articles/search-hashtag")) // GET /articles/search-hashtag 요청을 보낸다.
                .andExpect(status().isOk()) // 응답의 상태코드가 200인지 검증한다.
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 응답의 Content-Type이 text/html인지 검증한다.
                .andExpect(view().name("articles/search-hashtag")); // 뷰의 이름이 articles/search인지 검증한다.
    }

}

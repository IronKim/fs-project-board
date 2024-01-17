package com.fs.projectboard.service;

import com.fs.projectboard.domain.type.SearchType;
import com.fs.projectboard.dto.ArticleDto;
import com.fs.projectboard.dto.ArticleWithCommentsDto;
import com.fs.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true) // 읽기 전용 트랜잭션을 사용한다. 이렇게 하면 트랜잭션을 시작하고 종료하는 오버헤드를 줄일 수 있다.
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(ArticleDto dto) {
    }

    public void deleteArticle(long articleId) {
    }
}

package com.fs.projectboard.repository;

import com.fs.projectboard.domain.Article;
import com.fs.projectboard.domain.QArticle;
import com.fs.projectboard.repository.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom, // ArticleRepositoryCustom 인터페이스를 상속받는다.
        QuerydslPredicateExecutor<Article>,// QuerydslPredicateExecutor 인터페이스는 Querydsl을 사용하여 도메인 객체를 조회할 수 있게 해준다.
        QuerydslBinderCustomizer<QArticle> /* QuerydslBinderCustomizer 인터페이스는 Querydsl을 사용하여 도메인 객체를 조회할 때 사용할 바인더를 커스터마이징할 수 있게 해준다. */ {

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(Long articleId, String userid);

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) { // QuerydslBinderCustomizer 인터페이스의 customize 메소드를 구현한다.
        bindings.excludeUnlistedProperties(true); // 기본적으로 제공되는 모든 필드를 사용하지 않도록 설정한다.
        bindings.including(root.title, root.content, root.hashtags, root.createdAt, root.createdBy); // title, content, hashtags, createdAt, createdBy 필드만 사용하도록 설정한다.
        bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '${v}' / title 필드는 대소문자를 구분하지 않고 like 검색을 수행한다.
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${v}%' / title 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%' / content 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.
        bindings.bind(root.hashtags.any().hashtagName).first(StringExpression::containsIgnoreCase); // like '%${v}%' / hashtags 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // createdAt 필드는 eq 검색을 수행한다.
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%' / createdBy 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.

    }
}



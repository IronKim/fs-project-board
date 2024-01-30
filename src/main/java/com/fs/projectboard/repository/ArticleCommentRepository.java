package com.fs.projectboard.repository;

import com.fs.projectboard.domain.ArticleComment;
import com.fs.projectboard.domain.QArticleComment;
import com.fs.projectboard.domain.projection.ArticleCommentProjection;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(excerptProjection = ArticleCommentProjection.class)
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,// QuerydslPredicateExecutor 인터페이스는 Querydsl을 사용하여 도메인 객체를 조회할 수 있게 해준다.
        QuerydslBinderCustomizer<QArticleComment> /* QuerydslBinderCustomizer 인터페이스는 Querydsl을 사용하여 도메인 객체를 조회할 때 사용할 바인더를 커스터마이징할 수 있게 해준다. */ {

    List<ArticleComment> findByArticle_Id(Long articleId);
    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) { // QuerydslBinderCustomizer 인터페이스의 customize 메소드를 구현한다.
        bindings.excludeUnlistedProperties(true); // 기본적으로 제공되는 모든 필드를 사용하지 않도록 설정한다.
        bindings.including(root.content, root.createdAt, root.createdBy); // title, content, hashtag, createdAt, createdBy 필드만 사용하도록 설정한다.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%${v}%' / content 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // createdAt 필드는 eq 검색을 수행한다.
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%' / createdBy 필드는 대소문자를 구분하지 않고 contains 검색을 수행한다.
    }
}


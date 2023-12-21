package com.fs.projectboard.repository;

import com.fs.projectboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
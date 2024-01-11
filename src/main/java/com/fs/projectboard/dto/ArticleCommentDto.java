package com.fs.projectboard.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.fs.projectboard.domain.ArticleComment}
 */
public record ArticleCommentDto(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy,
                                String content) {
    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt,
                                       String modifiedBy, String content) {
        return new ArticleCommentDto(createdAt, createdBy, modifiedAt, modifiedBy, content);
    }
}
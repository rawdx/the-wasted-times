package com.newspaper.entities;

/**
 * Enum representing the status of an article.
 * - PUBLISHED: Article is published and visible to users.
 * - PENDING: Article is pending review or approval.
 * - DECLINED: Article has been declined and not published.
 */
public enum ArticleStatus {
    PUBLISHED,
    PENDING,
    DECLINED
}

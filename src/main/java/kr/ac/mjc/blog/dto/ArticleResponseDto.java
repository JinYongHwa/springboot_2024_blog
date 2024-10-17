package kr.ac.mjc.blog.dto;

import kr.ac.mjc.blog.domain.Article;

public class ArticleResponseDto {
    private boolean isSuccess;
    private Article article;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}

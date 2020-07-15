package com.yed.bean.weixin;

import java.util.List;

/**
 * 图文
 * @author hh
 *
 */
public class News {
    //文章列表
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}

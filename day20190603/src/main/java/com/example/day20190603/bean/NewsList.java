package com.example.day20190603.bean;

import java.util.ArrayList;

public class NewsList {
    private ArrayList<News> articles;

    public NewsList(ArrayList<News> articles) {
        this.articles = articles;
    }

    public NewsList() {
    }

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "NewsList{" +
                "articles=" + articles +
                '}';
    }
}

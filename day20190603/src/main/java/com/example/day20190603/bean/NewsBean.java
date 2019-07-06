package com.example.day20190603.bean;

public class NewsBean {

    private NewsList list;

    public NewsBean(NewsList list) {
        this.list = list;
    }

    public NewsBean() {
    }

    public NewsList getList() {
        return list;
    }

    public void setList(NewsList list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "list=" + list +
                '}';
    }
}

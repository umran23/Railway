package com.railway.demo.model;
import javax.persistence.*;

@Entity
@Table
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long News_id;
    @Column(nullable = false)
    private String news;
/*Getter and Setter*/
    public Long getNews_id() {
        return News_id;
    }

    public void setNews_id(Long news_id) {
        News_id = news_id;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
    /*Constructor Empty*/
    public News() {
    }
    /*Constructor Without ID*/
    public News(String news) {
        this.news = news;
    }
}

package com.lepekha.owoxtestapp.model.pojo;

/**
 * Created by Ruslan on 26.10.2017.
 */

import java.util.List;

/**Обьект для хранения json пришедшего от GET /search/photos*/
public class SearchPhoto {
    private int total;
    private int total_pages;
    private List<Photo> results;

    public SearchPhoto(int total, int total_pages, List<Photo> results) {
        this.total = total;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Photo> getResults() {
        return results;
    }

    public void setResults(List<Photo> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchPhoto{" +
                "total=" + total +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}';
    }
}

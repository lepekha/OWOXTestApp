package com.lepekha.owoxtestapp.model.pojo;

/**
 * Обьект для хранения ссылок на фото
 */

public class Links {
    private String self;
    private String html;
    private String download;

    public Links(String self, String html, String download) {
        this.self = self;
        this.html = html;
        this.download = download;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                ", html='" + html + '\'' +
                ", download='" + download + '\'' +
                '}';
    }
}

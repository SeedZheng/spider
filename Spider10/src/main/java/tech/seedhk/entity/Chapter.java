package tech.seedhk.entity;

import java.io.Serializable;

/**
 * Created by hasee on 2017/2/20.
 */
public class Chapter implements Serializable {

    private static final long serialVersionUID = -6303542739312686495L;
    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Chapter(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Chapter() {
        super();
    }
}

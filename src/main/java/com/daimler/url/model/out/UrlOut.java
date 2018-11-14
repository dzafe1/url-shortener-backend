package com.daimler.url.model.out;

import com.daimler.url.model.Url;

import java.io.Serializable;

public class UrlOut implements Serializable {

    private static final long serialVersionUID = -7269838009637845144L;

    private String shortUrl;

    private String fullUrl;


    public UrlOut() {
    }

    public UrlOut(Url url) {
        this.shortUrl = url.getShortUrl();
        this.fullUrl = url.getFullUrl();
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }
}

package com.daimler.url.model.in;

import org.hibernate.validator.constraints.URL;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UrlIn implements Serializable {

    private static final long serialVersionUID = 2817091998792887432L;

    @Nullable
    @Size(min = 1, max = 6, message = "Please enter custom URL within 6 characters!")
    private String customUrl;

    @Size(min = 3, max = 3000, message = "Please enter URL above 3 and bellow characters!")
    @URL(message = "Please enter valid URL!")
    private String fullUrl;

    public UrlIn() {
    }

    public UrlIn(String customUrl, String fullUrl) {
        this.customUrl = customUrl;
        this.fullUrl = fullUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getCustomUrl() {
        return customUrl;
    }
}

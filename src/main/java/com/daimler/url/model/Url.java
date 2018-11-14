package com.daimler.url.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "url", indexes = {
        @Index(name = "IDX_URL_full_url", columnList = "full_url"),
        @Index(name = "IDX_URL_short_url", columnList = "short_url")})
public class Url implements Serializable {

    private static final long serialVersionUID = -6758049503632772180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Date dateCreated;

    @Column(name = "full_url", length = 30000)
    private String fullUrl;

    @Column(name = "short_url", length = 7)
    private String shortUrl;

    @Column(name = "custom_url", columnDefinition = "boolean DEFAULT false", updatable = false)
    private Boolean customUrl;

    public Url() {
    }

    public Url(Long id, String fullUrl, String shortUrl, Boolean customUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
        this.customUrl = customUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Boolean getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(Boolean customUrl) {
        this.customUrl = customUrl;
    }

    @PrePersist
    public void prePersist() {
        dateCreated = DateTime.now().toDate();
    }
}

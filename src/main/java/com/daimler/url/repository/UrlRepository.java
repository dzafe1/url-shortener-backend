package com.daimler.url.repository;

import com.daimler.url.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByFullUrlAndCustomUrl(String fullUrl, Boolean customUrl);

    Url findByShortUrl(String shortUrl);
}

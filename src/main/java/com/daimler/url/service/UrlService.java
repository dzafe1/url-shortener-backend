package com.daimler.url.service;

import com.daimler.url.exception.ItemFoundException;
import com.daimler.url.model.Url;
import com.daimler.url.model.in.UrlIn;
import com.daimler.url.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private static final Logger log = LoggerFactory.getLogger(UrlService.class);

    @Autowired
    UrlRepository urlRepository;

    public Url processUrl(UrlIn urlIn) {
        log.info("Processing url {}", urlIn.getFullUrl());

        Url url = checkDoesUrlExistsByFullUrl(urlIn.getFullUrl());
        if (url != null)
            return url;

        String hash = RandomStringUtils.random(6, true, true);
        return urlRepository.saveAndFlush(new Url(null, urlIn.getFullUrl(), hash, false));
    }

    private Url checkDoesUrlExistsByFullUrl(String fullUrl) {
        log.info("Checking does url exists by full url {}", fullUrl);
        return urlRepository.findByFullUrlAndCustomUrl(fullUrl,false);
    }

    public Url processCustomUrl(UrlIn urlIn) {
        log.info("Processing custom url {}, {}", urlIn.getFullUrl(), urlIn.getCustomUrl());

        Url url = checkDoesUrlExistByCustomUrl(urlIn.getCustomUrl());
        if (url != null)
            throw new ItemFoundException("Please choose another custom url!");

        return urlRepository.saveAndFlush(new Url(null, urlIn.getFullUrl(), urlIn.getCustomUrl(), true));
    }

    private Url checkDoesUrlExistByCustomUrl(String customUrl) {
        log.info("Checking does url exists by custom url {}", customUrl);
        return urlRepository.findByShortUrl(customUrl);
    }


    public String getFullUrlFromShort(String hash) {
        Url url = urlRepository.findByShortUrl(hash);

        if (url == null)
            return "http://localhost:4201/error";

        return url.getFullUrl();
    }
}

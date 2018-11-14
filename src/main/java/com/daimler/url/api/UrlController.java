package com.daimler.url.api;

import com.daimler.url.model.in.UrlIn;
import com.daimler.url.model.out.UrlOut;
import com.daimler.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping( produces = MediaType.APPLICATION_JSON)
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/api/url")
    @ResponseBody
    public UrlOut processFullUrl(@Valid @RequestBody UrlIn urlIn) {
        return new UrlOut(urlService.processUrl(urlIn));
    }

    @PostMapping("/api/custom-url")
    @ResponseBody
    public UrlOut processCustomUrl(@Valid @RequestBody UrlIn urlIn) {
        return new UrlOut(urlService.processCustomUrl(urlIn));
    }

    @GetMapping("/{hash}")
    @ResponseBody
    public void getRedirected(HttpServletResponse httpServletResponse, @PathVariable("hash") String hash){
        httpServletResponse.setHeader("Location", urlService.getFullUrlFromShort(hash));
        httpServletResponse.setStatus(302);
    }

}

package com.tiny_url.url_shortner.controller;

import com.tiny_url.url_shortner.service.UrlFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping()
public class RedirectController {

    @Autowired
    private UrlFetchingService urlFetchingService;
    
    @GetMapping("/{id}")
    public RedirectView redirectWithUsingRedirectView(@PathVariable String id,
        RedirectAttributes attributes) {
            attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
            attributes.addAttribute("attribute", "redirectWithRedirectView");
            return new RedirectView(urlFetchingService.getRedirectUrl(id));
    }
}
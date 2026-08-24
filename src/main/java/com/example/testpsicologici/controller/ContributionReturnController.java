package com.example.testpsicologici.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContributionReturnController {

    private static final String NO_INDEX = "noindex, nofollow, noarchive";

    @GetMapping("/supporto/grazie")
    public String success(HttpServletResponse response, Model model) {
        return returnPage("success", response, model);
    }

    @GetMapping("/supporto/annullato")
    public String cancelled(HttpServletResponse response, Model model) {
        return returnPage("cancelled", response, model);
    }

    private String returnPage(String status, HttpServletResponse response, Model model) {
        response.setHeader("X-Robots-Tag", NO_INDEX);
        model.addAttribute("status", status);
        model.addAttribute("reactPageData", ReactPageData.of("supportReturn", "status", status));
        return "support-return";
    }
}

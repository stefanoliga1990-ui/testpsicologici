package com.example.testpsicologici.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class NotFoundRobotsHeaderFilter extends OncePerRequestFilter {

    private static final String ROBOTS_HEADER = "noindex, follow";

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Object errorStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (errorStatus instanceof Integer status && status == HttpServletResponse.SC_NOT_FOUND) {
            response.setHeader("X-Robots-Tag", ROBOTS_HEADER);
        }
        filterChain.doFilter(request, response);
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND && !response.isCommitted()) {
            response.setHeader("X-Robots-Tag", ROBOTS_HEADER);
        }
    }
}

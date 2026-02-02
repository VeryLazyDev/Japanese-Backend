package com.dev.japanese_app.middleware.jwt;

import com.dev.japanese_app.common.constant.SecurityConstants;
import com.dev.japanese_app.features.user.repo.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        for (String urlPattern : SecurityConstants.WHITE_LISTS_URL) {
            if (antPathMatcher.match(urlPattern, request.getRequestURI())){
                filterChain.doFilter(request,response);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}

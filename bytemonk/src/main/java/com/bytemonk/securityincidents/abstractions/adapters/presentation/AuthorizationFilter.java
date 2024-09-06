package com.bytemonk.securityincidents.abstractions.adapters.presentation;

import com.bytemonk.securityincidents.abstractions.domain.exceptions.ValidationException;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.AuthenticatedUserRequest;
import com.bytemonk.securityincidents.users.IUserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthorizationFilter implements Filter {
    private final IUserRepository anUserRepository;

    @Autowired
    public AuthorizationFilter(IUserRepository anUserRepository) {
        this.anUserRepository = anUserRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var anHttpRequest = (HttpServletRequest) servletRequest;

        if (isPublicRoute(anHttpRequest.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        var anAuthenticatedUser = getUserCredentials(anHttpRequest);

        var anHttpResponse = (HttpServletResponse) servletResponse;

        if (anAuthenticatedUser == null) {
            anHttpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        var anUser = anUserRepository.findByUsername(anAuthenticatedUser.getDomainUsername());

        if (anUser == null) {
            anHttpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (!anAuthenticatedUser.password().equals(anUser.getPassword().value())) {
            anHttpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        anHttpRequest.setAttribute("authenticatedUser", anUser);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private AuthenticatedUserRequest getUserCredentials(HttpServletRequest aRequest) {

        try {
            var anUsername = aRequest.getHeader("X-Authorization-Username");
            var aPassword = aRequest.getHeader("X-Authorization-Password");

            return new AuthenticatedUserRequest(anUsername, aPassword);
        }catch (ValidationException ex) {
            return null;
        }
    }

    private boolean isPublicRoute(String aRequestURI) {
        return aRequestURI.startsWith("/h2-console");
    }
}

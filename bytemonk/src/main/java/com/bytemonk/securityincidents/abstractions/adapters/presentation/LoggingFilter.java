package com.bytemonk.securityincidents.abstractions.adapters.presentation;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    private static final String LOG_FILE = "api-requests.log";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest anHttpRequest = (HttpServletRequest) servletRequest;

        logRequestDetails(anHttpRequest);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void logRequestDetails(HttpServletRequest aRequest) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            var timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

            printWriter.printf("timestamp: %s - ipAddress: %s - endpoint: %s - method: %s%n", timestamp, aRequest.getRemoteAddr(),
                    aRequest.getRequestURI(), aRequest.getMethod());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

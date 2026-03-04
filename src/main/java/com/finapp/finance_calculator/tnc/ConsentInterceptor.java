package com.finapp.finance_calculator.tnc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ConsentInterceptor implements HandlerInterceptor {

    private final ConsentSVC consentService;
    
    public ConsentInterceptor(ConsentSVC consentService) {
		this.consentService = consentService;
	}

    @Value("${app.consent.version}")
    private String VERSION;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();

        // Skip consent check for consent API itself
        if (uri.startsWith("/api/consent")) {
            return true;
        }

        // Only protect POST calculation endpoints
        if ("POST".equalsIgnoreCase(request.getMethod())) {

            boolean hasConsent = consentService.hasConsent(
                    resolveConsentType(uri),
                    VERSION,
                    request,
                    response
            );

            if (!hasConsent) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write(
                        "You must accept Terms & Conditions before using this feature."
                );
                return false;
            }
        }

        return true;
    }

    private String resolveConsentType(String uri) {

        if (uri.contains("/emi")) return "FULL";
        if (uri.contains("/sip")) return "FULL";
        if (uri.contains("/fd")) return "FULL";
        if (uri.contains("/affordability")) return "FULL";
        if (uri.contains("/loan")) return "FULL";

        return "FULL";
    }
}
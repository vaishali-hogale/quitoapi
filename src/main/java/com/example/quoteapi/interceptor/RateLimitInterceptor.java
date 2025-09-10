package com.example.quoteapi.interceptor;

import com.example.quoteapi.service.RateLimitService;
import com.github.bucket4j.Bucket;
import com.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);
    private final RateLimitService rateLimitService;

    public RateLimitInterceptor(RateLimitService rateLimitService) {
        this.rateLimitService = rateLimitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        Bucket bucket = rateLimitService.getBucket(ipAddress);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        logger.info("Request from IP: {}, Path: {}, Status: {}", ipAddress, request.getRequestURI(),
                probe.isConsumed() ? HttpStatus.OK.value() : HttpStatus.TOO_MANY_REQUESTS.value());

        if (probe.isConsumed()) {
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Rate limit exceeded. Try again in " + waitForRefill + " seconds.\"}");
            return false;
        }
    }
}
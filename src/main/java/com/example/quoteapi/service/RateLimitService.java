package com.example.quoteapi.service;

import com.github.bucket4j.Bandwidth;
import com.github.bucket4j.Bucket;
import com.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
    private final long capacity;
    private final Duration refillDuration;

    public RateLimitService(@Value("${rate.limit.capacity}") long capacity,
                           @Value("${rate.limit.refill}") String refillDuration) {
        this.capacity = capacity;
        this.refillDuration = Duration.parse("PT" + refillDuration.replace(" ", ""));
    }

    public Bucket getBucket(String ipAddress) {
        return buckets.computeIfAbsent(ipAddress, k -> {
            Refill refill = Refill.intervally(capacity, refillDuration);
            Bandwidth limit = Bandwidth.classic(capacity, refill);
            return Bucket.builder().addLimit(limit).build();
        });
    }
}
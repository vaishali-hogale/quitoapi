package com.example.quoteapi;

import com.example.quoteapi.service.RateLimitService;
import com.github.bucket4j.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RateLimitServiceTest {
    @Autowired
    private RateLimitService rateLimitService;

    @Test
    public void testRateLimit() {
        String ip = "127.0.0.1";
        Bucket bucket = rateLimitService.getBucket(ip);

        // Consume 5 tokens (within limit)
        for (int i = 0; i < 5; i++) {
            assertTrue(bucket.tryConsume(1), "Should allow request " + (i + 1));
        }

        // 6th request should be blocked
        assertFalse(bucket.tryConsume(1), "Should block 6th request");
    }
}

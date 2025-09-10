package com.example.quoteapi.controller;

import com.example.quoteapi.model.Quote;
import com.example.quoteapi.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Operation(summary = "Get a random inspirational quote")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a quote")
    @ApiResponse(responseCode = "429", description = "Rate limit exceeded")
    @GetMapping("/quote")
    public Quote getQuote() {
        return quoteService.getRandomQuote();
    }
}
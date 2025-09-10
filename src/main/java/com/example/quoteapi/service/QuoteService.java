ackage com.example.quoteapi.service;

import com.example.quoteapi.model.Quote;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final List<Quote> quotes = Arrays.asList(
        new Quote("The only way to do great work is to love what you do. - Steve Jobs"),
        new Quote("Stay hungry, stay foolish. - Steve Jobs"),
        new Quote("Your time is limited, so don't waste it living someone else's life. - Steve Jobs"),
        new Quote("Innovation distinguishes between a leader and a follower. - Steve Jobs"),
        new Quote("Design is not just what it looks like and feels like. Design is how it works. - Steve Jobs")
    );

    public Quote getRandomQuote() {
        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
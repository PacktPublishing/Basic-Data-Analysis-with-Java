/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import static java.time.DayOfWeek.MONDAY;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Example of how to stream through collections using the stock quote.
 * @author Erik Costlow
 */
public class StreamQuotes {
    private List<StockQuote> quotes = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        final StreamQuotes self = new StreamQuotes();
        self.go();
    }
    
    private void go() throws IOException{
        try (final InputStream in = QuoteParser.class.getResourceAsStream("hpe.csv");
                final Reader readerIn = new InputStreamReader(in);
                final LineNumberReader reader = new LineNumberReader(readerIn)) {
            reader.readLine(); //ignore the header row
            while(reader.ready()){
                final StockQuote quote = QuoteParser.parse(reader.readLine());
                quotes.add(quote);
            }
        }
        
        demonstrateStreams();
    }

    private void demonstrateStreams() {
        System.out.println("All quotes");
        quotes.forEach(System.out::println);
        
        System.out.println();
        Predicate<StockQuote> check = quote -> MONDAY.equals(quote.getDate().getDayOfWeek());
        final String  justClosing = quotes.stream()
                .filter(check)
                .map(quote -> nicelyFormat(quote))
                .collect(Collectors.joining(", "));
        System.out.println("Closing quotes: " + justClosing);
    }
    
    private String nicelyFormat(StockQuote quote){
        return quote.getDate().format(DateTimeFormatter.ISO_DATE)
                        + " closed at " + quote.getClose();
    }
    
    private void oldWay(){
        StringBuilder sb = new StringBuilder();
        for(StockQuote quote : quotes){
            if(MONDAY.equals(quote.getDate().getDayOfWeek())){
                String formatted = nicelyFormat(quote);
                sb.append(formatted);
            }
        }
    }
}

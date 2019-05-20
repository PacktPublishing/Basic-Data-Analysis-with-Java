/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section4;

import com.packtpub.basic.QuoteParser;
import com.packtpub.basic.StockQuote;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 *
 * @author erikc_000
 */
public class RandomSampling {

    public static void main(String[] args) throws IOException {
        approximatedRandom();
        constantNumber(10);
    }

    private static void approximatedRandom() throws IOException {
        final double pctWanted = .2;
        final Random random = new Random();
        final Path path = readStocks();
        final List<StockQuote> lines = Files.lines(path)
                .skip(1)//Ignore the header
                .filter(line -> random.nextDouble() < pctWanted)
                .map(QuoteParser::parse)
                .collect(Collectors.toList());
        
        System.out.println("Approximated Random " + lines.size() + " lines in total.");
        printDates(lines);
        
        final List<StockQuote> subsetLines = Files.lines(path)
                .skip(1)//Ignore the header
                //Assume not all lines qualify, use a filter
                .filter(line -> random.nextDouble() < pctWanted)
                .map(QuoteParser::parse)
                .filter(quote -> quote.getDate().isAfter(LocalDate.of(2016, 12, 1)))
                .collect(Collectors.toList());
        
        System.out.println("Approximated Random " + subsetLines.size() + " filtered lines.");
        printDates(subsetLines);
    }
    
    private static void constantNumber(int howMany) throws IOException {
        
        final Path path = readStocks();
        final int count = (int) Files.lines(path)
                .skip(1)//Ignore the header
                .count();
        
        final Random random = new Random();
        if(howMany>=count){
            throw new IllegalArgumentException("Sample size exceeds available");
        }
        final Set<Integer> chooseThese = new HashSet<>();
        while(chooseThese.size()<howMany){
            chooseThese.add(random.nextInt(count));
        }
        
        final AtomicInteger check = new AtomicInteger();
        final List<StockQuote> subsetLines = Files.lines(path)
                .skip(1)//Ignore the header
                //Same filter applies if subset wanted.
                .filter(line -> chooseThese.contains(check.getAndIncrement()))
                .map(QuoteParser::parse)
                .collect(Collectors.toList());
        
        System.out.println("Got " + subsetLines.size() + " lines.");
        printDates(subsetLines);
        //Not necessarily best performance, doesn't stop when full
    }

    private static Path readStocks() {
        final Path path = Paths.get("target", "classes", "com", "packtpub", "basic", "hpe.csv");
        if (!Files.exists(path)) {
            throw new RuntimeException("Unable to open sample file, project structure changed.");
        }
        return path;
    }
    
    private static void printDates(List<StockQuote> quotes){
        final String dates = quotes.stream()
                .map(quote -> quote.getDate().toString())
                .collect(Collectors.joining(", "));
        System.out.println("  " + dates);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.basic;

import com.packtpub.scalaish.Tuple2;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Convert from the CSV representation to something I can look at with named
 * Getters;
 *
 * @author Erik Costlow
 */
public class QuoteParser {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-MMM-yy");

    /**
     *
     * @param line
     * @return StockQuote from the line
     */
    public static StockQuote parse(String line) {
        final String[] split = line.split(",");
        if (split.length != 6) {
            return null;
        }
        final LocalDate date = LocalDate.parse(split[0], FORMATTER);
        final double open = Double.parseDouble(split[1]);
        final double high = Double.parseDouble(split[2]);
        final double low = Double.parseDouble(split[3]);
        final double close = Double.parseDouble(split[4]);
        final int volume = Integer.parseInt(split[5]);
        final StockQuote quote = new StockQuote(date, open, high, low, close, volume);
        return quote;
    }

    /**
     * Tuples are convenient to avoid unnecessary classes, but hard to remember
     * what they're for when you over-use.<br/>
     * It's a LocalDate and a Double - what do those stand for again?
     *
     * @param line
     * @return Tuple of date and closing price
     */
    public static Tuple2<LocalDate, Double> parseAsTuple(String line) {
        final String[] split = line.split(",");
        if (split.length != 6) {
            return null;
        }
        final LocalDate date = LocalDate.parse(split[0], FORMATTER);
        final double open = Double.parseDouble(split[1]);
        final double high = Double.parseDouble(split[2]);
        final double low = Double.parseDouble(split[3]);
        final double close = Double.parseDouble(split[4]);
        final Tuple2<LocalDate, Double> tuple = new Tuple2(date, close);
        return tuple;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.basic;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Erik Costlow
 */
public class StockQuote {
    private final LocalDate date;
    
    private final double open;
    
    private final double high;
    
    private final double low;
    
    private final double close;
    
    private final int volume;
    
    public StockQuote(LocalDate date, double open, double high, double low, double close, int volume){
        this.date=date;
        this.open=open;
        this.high=high;
        this.low=low;
        this.close=close;
        this.volume=volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getOpen() {
        return open;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public int getVolume() {
        return volume;
    }

    /**
     * 
     * @return based only on the date
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.date);
        return hash;
    }

    /**
     * 
     * @param obj
     * @return if the dates are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StockQuote other = (StockQuote) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StockQuote{" + "date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + '}';
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.basic;

import com.packtpub.basic.StockQuote;
import com.packtpub.basic.QuoteParser;
import static com.packtpub.basic.QuoteParser.parse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Erik Costlow
 */
public class QuoteParserTest {
    
    @Test
    public void testParseQuotes() throws IOException{
        try (final InputStream in = QuoteParser.class.getResourceAsStream("hpe.csv");
                final Reader readerIn = new InputStreamReader(in);
                final LineNumberReader reader = new LineNumberReader(readerIn)) {
            reader.readLine(); //ignore the header row
            while(reader.ready()){
                workWith(reader.getLineNumber(), reader.readLine());
            }
        }
    }
    
    private static void workWith(int line, String theLine) {
        System.out.println(line + "\t"+theLine);
        final StockQuote quote = parse(theLine);
        if(quote==null){
            Logger.getLogger(QuoteParser.class.getSimpleName()).log(Level.WARNING, "Unable to parse line {0}", line);
            fail("Unable to parse line " + line);
        }
        System.out.println("\t" + quote);
        //Try to reproduce the line.
        //As people change the data set over time, testing will help you catch when things go wrong.
        final String dateAsString = QuoteParser.FORMATTER.format(quote.getDate());
        final DecimalFormat df = new DecimalFormat("#.##");
        final String actual = String.format("%s,%s,%s,%s,%s,%d", dateAsString, df.format(quote.getOpen()), df.format(quote.getHigh()), df.format(quote.getLow()), df.format(quote.getClose()), quote.getVolume());
        assertEquals("Line " + line + " did not match", theLine, actual);
    }
}

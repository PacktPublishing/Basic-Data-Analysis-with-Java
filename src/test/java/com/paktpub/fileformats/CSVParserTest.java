/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.fileformats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;

/**
 *
 * @author erikc_000
 */
public class CSVParserTest {
    @Test
    public void test() throws IOException{
        final Path path = Paths.get("src", "test", "resources", "com", "paktpub", "fileformats", "numbers.csv");
        Files.lines(path)
                .map(line -> line.split(","))
                .forEach(CSVParserTest::showLine);
    }
    
    private static void showLine(String[] line){
        final String s = String.format("First is %s and last is %s", line[0], line[line.length-1]);
        System.out.println(s);
    }
}

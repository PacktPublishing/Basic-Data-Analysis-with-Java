/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erikc_000
 */
public class URLEncodingApp {
    public static void main(String[] args){
        final String packtUrl = "https://www.packtpub.com/all?search=";
        final String searchFor = "data analysis";
        
        final String urlStr = format(packtUrl, searchFor);
        try {
            final URL url = new URL(urlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(URLEncodingApp.class.getName()).log(Level.SEVERE, "Unable to make URL", ex);
        }
    }

    private static String format(String packtUrl, String searchFor) {
        String right=null;
        String wrong=packtUrl + searchFor;
        try {
            final String charset = StandardCharsets.UTF_8.name();
            System.out.println(charset);
            final String encodedSearchFor = URLEncoder.encode(searchFor, charset);
            right = packtUrl + encodedSearchFor;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(URLEncodingApp.class.getName()).log(Level.SEVERE, "Unable to convert", ex);
        }
        System.out.println("right is " + right);
        System.out.println("wrong is " + wrong);
        //Might look minor but gets more obvious with certain characters
        return wrong;
    }
}

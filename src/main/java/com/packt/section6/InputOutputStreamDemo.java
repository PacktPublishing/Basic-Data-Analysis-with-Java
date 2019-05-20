/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik Costlow
 */
public class InputOutputStreamDemo {

    public static void main(String[] args) throws MalformedURLException, IOException {
        final URL url = new URL("https://www.packtpub.com");
        pipedWay(url);
        System.out.println("=== Above is Piped Streams");
        System.out.println("=================");
        System.out.println("=== Below is basic transfer");
        basicWay(url);

    }
    
    private static void pipedWay(URL url) throws IOException{
        PipedInputStream pin = new PipedInputStream();
        PipedOutputStream pout = new PipedOutputStream(pin);
        
        System.out.println("Ready");

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("Writing to output stream...");
                    try (InputStream urlIn = url.openStream()) {
                        
                        
                        System.out.println("opened");
                        final byte[] bytes = new byte[2048];
                        int count = 0;
                        for (int length = urlIn.read(bytes); length > 0; length = urlIn.read(bytes)) {
                            pout.write(bytes, 0, length);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(InputOutputStreamDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(InputOutputStreamDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();

        System.out.println("Starting read...");
        int b;
        try {
            while ((b = pin.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException ex) {
            
        }
    }

    private static void basicWay(URL url){
        
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try (InputStream urlIn = url.openStream()) {
            final byte[] bytes = new byte[2048];
            for(int length=urlIn.read(bytes); length>=0; length=urlIn.read(bytes)){
                bout.write(bytes, 0, length);
            }
        }catch(IOException e){
            
        }
        
        System.out.println(bout.toString());
    }
}

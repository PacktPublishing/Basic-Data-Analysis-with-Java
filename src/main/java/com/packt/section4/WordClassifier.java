/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packt.section4;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.ui.standalone.ClassPathResource;

/**
 * Bayes-like word classifier, modeled after https://deeplearning4j.org/word2vec
 * (similar in that it makes associations)
 *
 * @author Erik Costlow
 */
public class WordClassifier {

    public static void main(String[] args) {
        try {
            final long start = System.currentTimeMillis();
            String filePath = new ClassPathResource("/com/packtpub/section4/PacktBlogs.txt").getFile().getAbsolutePath();
            final SentenceIterator b = new BasicLineIterator(filePath);

            TokenizerFactory t = new DefaultTokenizerFactory();
            t.setTokenPreProcessor(new CommonPreprocessor());
            
            System.out.println("Building model....");
            Word2Vec vec = new Word2Vec.Builder()
                    .minWordFrequency(5)
                    .iterations(1)
                    .layerSize(100)
                    .seed(42)
                    .windowSize(5)
                    .iterate(b)
                    .tokenizerFactory(t)
                    .build();
            System.out.println((System.currentTimeMillis() - start)/1000 + " seconds");
            System.out.println("Fitting Word2Vec model....");
            vec.fit();
            System.out.println((System.currentTimeMillis() - start)/1000 + " seconds");
            //WordVectorSerializer.writeWordVectors(vec, "pathToWriteto.txt");
            System.out.println("Finding nearest words...");
            final String wordA = "docker";
            Collection<String> lst = vec.wordsNearest(wordA, 10);
            final String closest = lst.stream().collect(Collectors.joining(", "));
            System.out.println("Closest words to " + wordA + " are " + closest);
            
            final String wordB = "game";
            Collection<String> lstB = vec.wordsNearest(wordB, 10);
            final String closestB = lstB.stream().collect(Collectors.joining(", "));
            System.out.println("Closest words to " + wordB + " are " + closestB);
            //There is a much better corpus of words on the main link.
        } catch (FileNotFoundException e) {

        }
    }
}

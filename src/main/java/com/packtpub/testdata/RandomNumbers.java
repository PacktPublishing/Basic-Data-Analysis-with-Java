/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.testdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Demonstrate the difference in random number types: random and Gaussian.
 *
 * @author Erik Costow
 */
public class RandomNumbers extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Randomization Demo");
        final ScatterChart<Number, Number> chart = plotRandom();
        VBox.setVgrow(chart, Priority.ALWAYS);
        final VBox pane = new VBox(new Label("Random Chart"), chart);

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ScatterChart<Number, Number> plotRandom() {
        final NumberAxis xAxis = new NumberAxis(0, 100, 10);
        final NumberAxis yAxis = new NumberAxis(0, 100, 25);
        final ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);

        final Random random = new Random();

        final XYChart.Series randomSeries = new XYChart.Series();
        randomSeries.setName("Random Int");
        populate(randomSeries, () -> random.nextInt(100));

        final int gaussianStdDev = 5;
        final int gaussianMean = 50;
        
        final XYChart.Series gaussianSeries = new XYChart.Series();
        gaussianSeries.setName("Gaussian Int, std dev " + gaussianStdDev + " near " + gaussianMean);

        populate(gaussianSeries, () -> (int) (random.nextGaussian() * gaussianStdDev + gaussianMean));

        sc.getData().addAll(randomSeries, gaussianSeries);
        return sc;
    }

    private static void populate(XYChart.Series me, IntSupplier supplier) {
        for (int i = 0; i < 100; i++) {
            me.getData().add(new XYChart.Data(i, supplier.getAsInt()));
        }

        final List<String> myList = new ArrayList<>();
    }
}

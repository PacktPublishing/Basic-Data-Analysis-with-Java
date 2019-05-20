/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.visualize;

import com.packtpub.basic.QuoteParser;
import com.packtpub.basic.StockQuote;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.time.format.DateTimeFormatter;
import java.util.DoubleSummaryStatistics;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Visualize the stock chart information as a line chart.
 *
 * @author Erik Costlow
 */
public class VisualizeFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Visualize Stock");
        final LineChart<String, Number> chart = plotStockData();
        VBox.setVgrow(chart, Priority.ALWAYS);
        final VBox pane = new VBox(chart);

        chart.getData().stream()
                .flatMap(el -> el.getData().stream())
                .forEach(d -> Tooltip.install(d.getNode(),
                        new Tooltip(d.getXValue() + " @ " + d.getYValue())));

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private LineChart<String, Number> plotStockData() {
        final XYChart.Series<String, Number> series = new XYChart.Series();
        try (InputStream in = StockQuote.class.getResourceAsStream("hpe.csv");
                final InputStreamReader reader = new InputStreamReader(in);
                LineNumberReader lineReader = new LineNumberReader(reader)) {
            lineReader.readLine();//Ignore the header row
            for (String line = lineReader.readLine(); line != null; line = lineReader.readLine()) {
                final StockQuote quote = QuoteParser.parse(line);
                final XYChart.Data<String, Number> data
                        = new XYChart.Data<>(quote.getDate()
                                .format(DateTimeFormatter.ISO_DATE), quote.getClose());
                series.getData().add(data);
            }
        } catch (IOException ex) {
            Logger.getLogger(VisualizeFX.class.getName()).log(Level.SEVERE, null, ex);
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final DoubleSummaryStatistics summary = series.getData().stream()
                .collect(Collectors.summarizingDouble(item -> item.getYValue().doubleValue()));
        final NumberAxis yAxis = new NumberAxis(summary.getMin(), summary.getMax(), 25);
        final LineChart<String, Number> sc = new LineChart<>(xAxis, yAxis);
        sc.getData().add(series);

        return sc;
    }
}

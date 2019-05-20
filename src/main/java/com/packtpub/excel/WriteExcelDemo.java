/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.excel;

import com.packtpub.basic.QuoteParser;
import com.packtpub.basic.StockQuote;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.util.Date;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Erik Costlow
 */
public class WriteExcelDemo {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        final WriteExcelDemo demo = new WriteExcelDemo();
        demo.go();
    }

    void go() throws IOException, InvalidFormatException {
        final Path dest = Paths.get("target", "WithQuotes.xlsx");
        try (InputStream in = WriteExcelDemo.class.getResourceAsStream("Preformatted.xlsx");
                OutputStream out = Files.newOutputStream(dest, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            OPCPackage pkg = OPCPackage.open(in);
            Workbook wb = new XSSFWorkbook(pkg);

            insertChartData(wb);

            wb.write(out);
        }
        System.out.println("File written to " + dest.toAbsolutePath());
    }

    private void insertChartData(Workbook wb) throws IOException {
        final CreationHelper createHelper = wb.getCreationHelper();
        final CellStyle dateStyle = wb.createCellStyle();

        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
        final CellStyle dollarStyle = wb.createCellStyle();
        final short dollarFormat = createHelper.createDataFormat().getFormat("_($* #,##0.00_);_($* (#,##0.00);_($* \"-\"??_);_(@_)"); //is the "Accounting" format.
        dollarStyle.setDataFormat(dollarFormat);
        Sheet sheet = wb.getSheetAt(0);

        int line = 1;
        try (final InputStream in = QuoteParser.class.getResourceAsStream("hpe.csv");
                final Reader readerIn = new InputStreamReader(in);
                final LineNumberReader reader = new LineNumberReader(readerIn)) {
            reader.readLine(); //ignore the header row
            while (reader.ready()) {
                final StockQuote quote = QuoteParser.parse(reader.readLine());

                final Row row = sheet.createRow(line);

                line++;
                //Not everything supports LocalDate yet, since it's JDK 8
                Date date = Date.from(quote.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Cell dateCell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                dateCell.setCellStyle(dateStyle);
                dateCell.setCellValue(date);
                setCellNumber(row, 1, dollarStyle, quote.getOpen());
                setCellNumber(row, 2, dollarStyle, quote.getHigh());
                setCellNumber(row, 3, dollarStyle, quote.getLow());
                setCellNumber(row, 4, dollarStyle, quote.getClose());
                setCellNumber(row, 5, null, quote.getVolume());
            }
        }
    }

    private void setCellNumber(Row row, int col, CellStyle style, double number) {
        final Cell closeCell = row.getCell(col, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        if (style != null) {
            closeCell.setCellStyle(style);
        }
        closeCell.setCellValue(number);
    }
}

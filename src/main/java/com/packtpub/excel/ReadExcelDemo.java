/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.excel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Erik Costlow
 */
public class ReadExcelDemo {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        final Path dest = Paths.get("target", "WithQuotes.xlsx");
        if(!Files.exists(dest)){
            new WriteExcelDemo().go();
        }
        
        final ReadExcelDemo self = new ReadExcelDemo();
        try(Workbook wb = WorkbookFactory.create(dest.toFile())){
            self.go(wb);
        }
        
    }
    
    private void go(Workbook wb){
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        Sheet sheet1 = wb.getSheetAt(0);
        for (Row row : sheet1) {
                for (Cell cell : row) {
                    CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    switch (cell.getCellTypeEnum()) {
                        case STRING:
                            System.out.print(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue());
                            } else {
                                System.out.print(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            final CellValue cv = evaluator.evaluate(cell);
                            final String evaluated = cv.formatAsString();
                            System.out.print("[Formula " + cell.getCellFormula() + " is " + evaluated + ']');
                            break;
                        case BLANK:
                            break;
                        default:
                            throw new UnsupportedOperationException("Unable to evaluate cell " + cellRef.toString());
                    }
                    System.out.print("\t");
                }
                System.out.println();
            }
    }
}

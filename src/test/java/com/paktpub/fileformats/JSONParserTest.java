/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paktpub.fileformats;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Test;

/**
 *
 * @author Erik Costlow
 */
public class JSONParserTest {

    /**
     * Basic reading of a JSON file without a POJO.
     *
     * @throws IOException
     */
    @Test
    public void testReadBasic() throws IOException {
        JsonFactory factory = new JsonFactory();
        final PrintStream out = System.out;
        try (InputStream in = JSONParserTest.class.getResourceAsStream("basic.json")) {
            JsonParser parser = factory.createParser(in);
            int indent = 0;
            while (!parser.isClosed()) {
                JsonToken jsonToken = parser.nextToken();
                if (jsonToken != null) {
                    switch (jsonToken) {
                        case FIELD_NAME:
                            printIndented(out, indent, parser.getCurrentName() + " is ");
                            break;
                        case START_OBJECT:
                            indent++;
                            out.println();
                            break;
                        case END_OBJECT:
                            indent--;
                            out.println();
                            break;
                        case VALUE_STRING:
                            printIndented(out, indent, parser.getText());
                            out.println();
                            break;
                        case VALUE_NUMBER_INT:
                        case VALUE_NUMBER_FLOAT:
                            printIndented(out, indent, parser.getNumberValue());
                            out.println();
                        default:
                        //ignore
                    }

                }
            }
        }
    }

    private void printIndented(PrintStream out, int indent, Object obj) {
        for (int i = 0; i < indent; i++) {
            out.print(' ');
        }
        out.print(obj);
    }
}

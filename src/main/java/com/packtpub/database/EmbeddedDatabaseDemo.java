/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik Costlow
 */
public class EmbeddedDatabaseDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        final Path tempDir = Files.createTempFile("packtDB", "");
        final String dir = tempDir.toAbsolutePath().toString();
        Class.forName("org.h2.Driver");
        System.out.println("Created demo database in " + dir + " - use h2 tools to view separately");
        try (
                Connection conn = DriverManager.
                        getConnection("jdbc:h2:" + dir, "", "")) {
                    final EmbeddedDatabaseDemo self = new EmbeddedDatabaseDemo(conn);
                    self.go();
                }
    }

    private final Connection conn;

    private EmbeddedDatabaseDemo(Connection conn) {
        this.conn = conn;
        createTables();
        populateRandomData();
    }

    private void createTables() {
        try {
            final Statement st = conn.createStatement();
            st.execute("CREATE TABLE person(id INTEGER NOT NULL, first_name VARCHAR(255), last_name VARCHAR(255), PRIMARY KEY(id))");
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmbeddedDatabaseDemo.class.getName()).log(Level.SEVERE, "Unable to create tables", ex);
        }
    }

    private void populateRandomData() {
        //TODO
    }
    
    private void go(){
        
    }
}

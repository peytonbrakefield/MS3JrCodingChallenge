package net.sqlitetutorial;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class CSVtoSQLiteDB {
    public static void main(String[] args) {
        //Variable declarations
        CSVtoSQLiteDB newDB = new CSVtoSQLiteDB();
        String fileName = args[0];
        String databaseFile = fileName + ".db";
        String badRecordFile = fileName.replace(".csv", "-bad.csv");
        String logFile = fileName.replace(".csv",".log");
        long badRec = 0;
        long validRec = 0;
        long totRec = 0;
        boolean isOk = true;

        //Opening invalid records file for writing during csv traversal
        try {
            File badRecords = new File(badRecordFile);
            badRecords.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }

        //Creating database, code inspired by https://www.sqlitetutorial.net/sqlite-java/create-table/

        databaseFile = databaseFile.replace(".csv", "");
        String url = "jdbc:sqlite:C:/sqlite/db/" + databaseFile;
        String sql = "CREATE TABLE IF NOT EXISTS records (\n"
                + "     A TEXT NOT NULL,\n"
                + "     B TEXT NOT NULL,\n"
                + "     C TEXT NOT NULL,\n"
                + "     D TEXT NOT NULL,\n"
                + "     E TEXT NOT NULL,\n"
                + "     F TEXT NOT NULL,\n"
                + "     G TEXT NOT NULL,\n"
                + "     H TEXT NOT NULL,\n"
                + "     I TEXT NOT NULL,\n"
                + "     J TEXT NOT NULL\n"
                + ");";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            /*if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }*/

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //This is where the CSV file is opened and parsed. The invalid records file is also prepared for being written in.

        try {
            FileWriter badRecordWriter = new FileWriter(badRecordFile);
            CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;
            nextLine = reader.readNext();

            //Ignoring the first line of the CSV file which indicates columns, read each line, and if that line contains a
            //member with a blank element, record it as an invalid record. If not, write it to the database file.
            //During each step, an integer is incremented for either invalid records or valid records, as well as the total
            //records integer.
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    for (int i = 0; i < nextLine.length; i++) {
                        if (nextLine[i].equals("")  || nextLine[i].equals(" ")) {
                            isOk = false;
                            badRec++;
                            i = nextLine.length;
                            for (String s : nextLine) {
                                badRecordWriter.write(s + ", ");
                            }
                            badRecordWriter.write("\n");
                            break;
                        }
                    }
                    if (isOk) {

                        newDB.insert(url, nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5],
                                nextLine[6], nextLine[7], nextLine[8], nextLine[9]);
                        validRec++;
                    }
                }
                totRec++;
                isOk = true;
            }
            badRecordWriter.close();

            //Writing stats to the log file.
            FileWriter statLog = new FileWriter(logFile);
            statLog.write("Total records: "+ (int) totRec + "\n");
            statLog.write("Valid records: " + (int) validRec + "\n");
            statLog.write("Bad records: " + (int) badRec + "\n");
            statLog.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Insert function for adding CSV data into the records table in the database file. Code inspired by https://www.sqlitetutorial.net/sqlite-java/insert/

    public void insert(String url, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j){
        String insertStmt = "INSERT INTO records(a, b, c, d, e, f, g, h, i, j) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(insertStmt)) {
            pstmt.setString(1, a);
            pstmt.setString(2, b);
            pstmt.setString(3, c);
            pstmt.setString(4, d);
            pstmt.setString(5, e);
            pstmt.setString(6, f);
            pstmt.setString(7, g);
            pstmt.setString(8, h);
            pstmt.setString(9, i);
            pstmt.setString(10, j);
            pstmt.executeUpdate();

        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}

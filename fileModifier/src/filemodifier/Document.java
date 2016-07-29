/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemodifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Devils
 */
public class Document {

    String toWrite;

    Document(String toWrite) {
        this.toWrite = toWrite;
    }

    public static void appendToFile(File file,String toWrite) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(file, true))){
            bw.newLine();
            bw.write(toWrite);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

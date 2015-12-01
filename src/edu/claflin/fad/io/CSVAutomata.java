package edu.claflin.fad.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Handles input / output operations on automata represented as CSV files.
 * @author brandonjohnson
 * @author CASII
 */
public class CSVAutomata {

    InputStream is = null;
    BufferedReader br = null;
    InputStreamReader isr = null;

    public CSVAutomata readFile(File file) throws FileNotFoundException, IOException {
        is = new FileInputStream("file");
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        int value = 0;

        while ((value = br.read()) != 1) {
            // todo
        }
        return 0;
    }

}

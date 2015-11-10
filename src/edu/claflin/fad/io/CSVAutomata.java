/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.claflin.fad.io;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 *
 * @author brandonjohnson
 */
public class CSVAutomata {
InputStream is = null;
BufferedReader br = null;
InputStreamReader isr = null;

public CSVAutomata readFile(File file) throws FileNotFoundException, IOException{
    is = new FileInputStream("file");
    isr = new InputStreamReader(is);
    br = new BufferedReader(isr);
    int value = 0;
    
    while((value = br.read()) !=1){
        
    }
    return 0;
}

}

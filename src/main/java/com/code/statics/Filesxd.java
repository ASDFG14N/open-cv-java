package com.code.statics;

import com.code.routes.Routes;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Gian
 */
public class Filesxd {

    final public static void saveTextFile(String fileName, String text, boolean append, ArrayList<String> arrayAux) {
        FileWriter fr = null;
        try {
            fr = new FileWriter(fileName, append);
            try (PrintWriter pw = new PrintWriter(fr)) {
                if (append && arrayAux != null) {
                    for (String str : arrayAux) {
                        pw.println(str);
                    }
                    return;
                }
                pw.println(text);
            }
        } catch (IOException ex) {
            System.out.println("Saving error: " + ex.toString());
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.out.println("Error closing stream: " + e.toString());
                }
            }
        }
    }

}

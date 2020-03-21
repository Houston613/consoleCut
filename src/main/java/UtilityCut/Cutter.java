package UtilityCut;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cutter {
    private final String charsetInput;
    private final String charsetOutput;
    private boolean symbol = false;
    //если флаг -w - true, то работа посимвольно
    private int opening;
    private int ending;

    public Cutter(String charsetInput, String charsetOutput) {
        //конструктору подается начало строки, конец,символьное ли деление, название выходного файла
        this.charsetInput = charsetInput;
        this.charsetOutput = charsetOutput;
    }
    //текст читается по строкам, поэтому создаю лист строк 
    public List<String> recode(InputStream in, OutputStream out,String inputName) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, charsetInput)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, charsetOutput)) {
                File file;
                List<String> text = new ArrayList<>();
                if(inputName.equals(""))
                    in = System.in;
                    //я не очень понял, нужно ли записывать так или использовать Scanner
                else
                    file = new File(inputName);
                while (sym != -1) {
                    writer.write(sym);
                    count++;
                    sym = reader.read();
                }
                return count;
            }
        }
    }

    public int recode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return recode(inputStream, outputStream);
            }
        }
    }

}

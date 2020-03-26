package UtilityCut;

import java.io.*;

public class ReaderAndWriter {
    String inputName;
    String outputName;
    Cutter cutter;
    PrintStream out;
    public ReaderAndWriter(String inputName, String outputName, Cutter cutter){
        this.cutter = cutter;
        this.inputName = inputName;
        this.outputName = outputName;
    }
    public void RaW(String inputName, String outputName) throws IOException {
        if (outputName.equals(".")) {
            out = System.out;
        } else {
            out = new PrintStream(new File(outputName));
        }
        InputStream in;
        if (inputName.equals(".")) {//если имя не изменилось
            in = System.in;
        } else {
            File input = new File(inputName);
            in = new FileInputStream(input);
        }
        System.setIn(in);
        System.setOut(out);
        try (BufferedReader bufRead = new BufferedReader(new InputStreamReader(in))) {

            String buf = bufRead.readLine();
            while (buf != null) {
                System.out.println(cutter.cutting(buf));
                System.out.println(System.lineSeparator());
                buf = bufRead.readLine();
            }
            in.close();
        }
    }
}

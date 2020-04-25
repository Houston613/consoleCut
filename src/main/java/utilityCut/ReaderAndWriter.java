package utilityCut;

import java.io.*;

public class ReaderAndWriter {
    private final Cutter cutter;

    public ReaderAndWriter(Cutter cutter){
        this.cutter = cutter;
    }
    public void readingAndWriting(File inputName, File outputName) throws IOException {
        PrintStream out;
        if (outputName.getPath().equals(".")) {
            out = System.out;
        } else {
            out = new PrintStream(new File(outputName.getPath()));
        }
        InputStream in;
        if (inputName.getPath().equals(".")) {//если имя не изменилось
            in = System.in;
        } else {
            File input = new File(inputName.getPath());
            in = new FileInputStream(input);
        }
        System.setOut(out);
        try (BufferedReader bufRead = new BufferedReader(new InputStreamReader(in))) {
            String buf = bufRead.readLine();
            while (buf != null) {
                System.out.println(cutter.cutting(buf));
                buf = bufRead.readLine();
            }
        }
    }
}

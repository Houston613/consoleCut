package UtilityCut;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.ArrayList;

public class CutterLauncher {

    @Option(name = "-o", metaVar = "OutputName", required = true, usage = "Output file name")
    private String outputFileName;

    @Option(name = "-c", metaVar = "Work with Symbols", usage = "Working with Symbols", forbids={"-w"})
    private boolean c;

    @Option(name = "-w", metaVar = "Work with Words",  usage = "Working with Words", forbids={"-c"})
    private boolean w;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String inputFileName;

    @Argument(required = true, metaVar = "range", index = 1, usage = "range of cutting")
    private String range;

    public static void main(String[] args) {
        new CutterLauncher().launch(args) ;
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar InputFile -o OutputName -c or -w range");
            parser.printUsage(System.err);
            return;
        }
        try {
            String[] r = range.split("-");
            int opening;
            int ending;
            boolean isEnd;
            boolean isStart;
                /* в r будет хранится либо
                1)число, число, тогда оба использую как начало и конец
                2)пустота,число, тогда первое ставлю стандартным, второе конец
                3)число, пустота, тогда первое ставлю как начало, а второе length строки-1
                */
            if (r[0].equals("-")) {
                ending = Integer.parseInt(r[1]);
                isEnd = true;
            } else if (r[0].matches("[0-9]+"))
                if (r[1].matches("[0-9]+")) {
                    opening = Integer.parseInt(r[0]);
                    ending = Integer.parseInt(r[1]);
                    isStart = true;
                    isEnd = true;
                } else if (r[1].matches("-")) {
                    opening = Integer.parseInt(r[0]);
                    isStart = true;
                }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

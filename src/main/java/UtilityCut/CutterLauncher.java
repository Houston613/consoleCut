package UtilityCut;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

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
        new CutterLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar InputFile -o OutputName -c/w range");
            parser.printUsage(System.err);
            return;
        }
        try {

            System.out.println("Total of " + result + " symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

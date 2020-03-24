package UtilityCut;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class Config {
    private int k;

    @Option(name = "-o", metaVar = "OutputName", required = true, usage = "Output file name")
    private File out = new File(".");

    @Option(name = "-c", metaVar = "Work with Symbols", usage = "Working with Symbols", forbids={"-w"})
    private boolean c = false;

    @Option(name = "-w", metaVar = "Work with Words",  usage = "Working with Words", forbids={"-c"})
    private boolean w = false;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private File in = new File(".");


    @Option(required = true, metaVar = "range", name="-r", usage = "range of cutting")
    private void range(String range){
        this.k=-Integer.parseInt(range);
    }
    //получает на вход строку,запускат
    void config(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            //вооот тут
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar InputFile -o OutputName -c or -w range");
            parser.printUsage(System.err);
            return;
        }
    }
    }

package UtilityCut;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class Config {
    private boolean symbol;
    //если флаг -w - true, то работа po slovam
    private boolean isEnd;
    private boolean isStart;
    private int opening;
    private int ending;
    private String inputName;
    private String outputName;


    @Option(name = "-o", metaVar = "OutputName", required = true, usage = "Output file name")
    private File out = new File(".");

    @Option(name = "-c", metaVar = "Work with Symbols", usage = "Working with Symbols", forbids = {"-w"})
    private boolean c = false;

    @Option(name = "-w", metaVar = "Work with Words", usage = "Working with Words", forbids = {"-c"})
    private boolean w = false;

    @Argument(metaVar = "InputName", usage = "Input file name")
    private File in = new File(".");


    @Option(required = true, metaVar = "range", name = "-r", usage = "range of cutting")
    private void range(String range) {
        if (range.matches("-[0-9]+")) {
            this.ending = -Integer.parseInt(range);
            this.isEnd = true;
            this.isStart = false;
        } else if (range.matches("[0-9]+-")) {
            this.opening = Integer.parseInt(range.replaceAll("-", ""));
            this.isEnd = false;
            //определится позже в чекере, тк не знаем пока длину строки
            this.isStart = true;
        } else if (range.matches("[0-9]+-[0-9]+")) {
            this.opening = Integer.parseInt(range.split("-")[0]);
            this.ending = Integer.parseInt(range.split("-")[1]);
            this.isEnd = true;
            this.isStart = true;
        }
    }

    public Config(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            this.inputName = in.getName();
            this.outputName = out.getName();
            if (w)
                this.symbol = false;
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Cut.jar InputFile -o OutputName -c or -w range");
            parser.printUsage(System.err);
            throw new IllegalArgumentException();
        }
    }
    public int getOpening(){
        return opening;
    }
    public int getEnding(){
        return ending;
    }
    public boolean getIsEnd(){
        return isEnd;
    }
    public boolean getIStart(){
        return isStart;
    }
    public boolean getSymbol(){
        return symbol;
    }
    public String getInputName(){
        return inputName;
    }
    public String getOutputName(){
        return outputName;
    }
}

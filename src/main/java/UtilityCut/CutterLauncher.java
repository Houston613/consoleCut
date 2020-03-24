package UtilityCut;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
    public class CutterLauncher {
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

        //как правильно указывать все ето? через конструктор в каттере ведь?
        //но что делать с всякими переменными, получаемыми из  range?

        void launch(String[] args) {
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
            try {
                String[] r = range.split("-");
                int opening = 0;
                int ending = 0;
                boolean isEnd = false;
                boolean isStart = false;
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
                if (w)
                    c = false;
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

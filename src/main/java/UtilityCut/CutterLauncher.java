package UtilityCut;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
    public class CutterLauncher {
        void launch(String[] args) {
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

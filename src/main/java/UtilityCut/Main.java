package UtilityCut;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
            Config config = new Config(args);
            boolean symbol = config.getSymbol();
            boolean isEnd = config.getIsEnd();
            boolean isStart = config.getIStart();
            int opening = config.getOpening();
            int ending = config.getEnding();
            File inputName = config.getInput();
            File outputName = config.getOut();
            Cutter cut = new Cutter(opening, ending, isEnd, isStart, symbol);
            ReaderAndWriter readerAndWriter = new ReaderAndWriter(cut);
            readerAndWriter.readingAndWriting(inputName, outputName);
    }
}

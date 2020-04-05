package UtilityCut;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            Config config = new Config(args);
            boolean symbol = config.getSymbol();
            boolean isEnd = config.getIsEnd();
            boolean isStart = config.getIStart();
            int opening = config.getOpening();
            int ending = config.getEnding();
            //может лучше так сделать? я же итак создаю экземпляр конфига, нет смысла еще раз его в логике запскать
            //или есть?
            String inputName = config.getInputName();
            String outputName = config.getOutputName();
            Cutter cut = new Cutter(opening, ending, isEnd, isStart, symbol);
            ReaderAndWriter readerAndWriter = new ReaderAndWriter(inputName, outputName, cut);
            readerAndWriter.RaW(inputName, outputName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package UtilityCut;

import java.io.*;
import java.io.Writer;

public class Main {
    public static void main(String[] args) throws IOException {
        Config config = new Config(args);
        boolean symbol = config.getSymbol();
        //если флаг -w - true, то работа po slovam
         boolean isEnd = config.getIsEnd();
         boolean isStart = config.getIStart();
         int opening = config.getOpening();
         int ending = config.getEnding();
         String inputName = config.getInputName();
         String outputName = config.getOutputName();
         Cutter cut = new Cutter(opening,ending,isEnd,isStart,symbol);
         ReadAndWrite readAndWrite = new ReadAndWrite(inputName, outputName, cut);
         readAndWrite.RaW(inputName, outputName);
        //сначала создаю конфиг-конструктор отдельно, там собираю все переменные
        //создаю читалку, она переписывает мой текст в ОТДЕЛЬНЫЙ файл
        //каттер обрабатывает и перезаписывает этот файл
        //райтер читает файл и выводит или на консоль, или просто этот файл
        }
    }

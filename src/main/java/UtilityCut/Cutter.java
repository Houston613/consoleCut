package UtilityCut;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Cutter {
    private final String charsetInput;
    private final String charsetOutput;
    private boolean symbol;
    //если флаг -w - true, то работа посимвольно
    private boolean isEnd;
    private boolean isStart;
    private int opening;
    private int ending;

    public Cutter(String charsetInput, String charsetOutput,
                  boolean symbol, boolean isEnd, boolean isStart, int opening, int ending) {
        this.charsetInput = charsetInput;
        this.charsetOutput = charsetOutput;
        this.symbol = symbol;
        this.isEnd = isEnd;
        this.isStart = isStart;
        this.opening = opening;
        this.ending = ending;
    }
//вроде неправильно создан конструктор. но как мне всякие isEnd объявить, если их значения в лаунчере задаются?

    public String cutting(String lines, boolean symbol, boolean isEnd, boolean isStart) {
        int start;
        int end;
        //значения начала и конца могут не подаваться в метод, поэтому нужно записать их вот так
        StringBuilder line = new StringBuilder();
        if (symbol) {
            if ((isEnd && isStart) && (opening < line.length()) && (ending < line.length())) {
                //если есть начало и конец
                start = opening;
                end = ending;
            } else if ((isEnd && !isStart) && (ending < line.length())) {
                //только конец
                start = 0;
                end = ending;
            } else if ((!isEnd && isStart) && (opening < line.length())) {
                //только начало
                start = opening;
                end = lines.length() - 1;
            } else return null;
            line.append(lines, start, end);
            //append сказал что можно без цикла, а я и не против
        } else {
            String[] listOfWords = lines.split(" ");
            //теперь не по буквам, а по словам
            if ((isEnd && isStart) && (opening < listOfWords.length) && (ending < listOfWords.length)) {
                start = opening;
                end = ending;
            } else if ((isEnd && !isStart) && (ending < listOfWords.length)) {
                start = 0;
                end = ending;
            } else if ((!isEnd && isStart) && (opening < listOfWords.length)) {
                start = opening;
                end = lines.length() - 1;
            } else return null;
            //для того чтобы избежать копипасты я в if объявляю значения начала и конца, а потом просто по ним двигаюсь
            while (start >= end) {
                line.append(listOfWords[start]);
                start++;
            }
        }
        return line.toString();
    }

    public void recode(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, charsetInput)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, charsetOutput)) {
                BufferedReader bufRead = new BufferedReader(reader);
                BufferedWriter bufWrite = new BufferedWriter(writer);
                String buf = bufRead.readLine();
                while (buf != null) {
                    bufWrite.write(cutting(buf, symbol, isEnd, isStart));
                    bufWrite.newLine();
                    //мне же нужно ето или он автоматом перехдит?
                    //убрал лист, понял что есть же writer
                    buf = bufRead.readLine();
                }
            }
        }
    }

    public void recode(String inputName, String outputName) throws IOException {
        //if (inputName.equals(""))

        //пока не понимаю, как поставить условие так, чтобы можно было вводить/выводить текст с консоли.
        //и будет ли такой способ (как в примере) работать

        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                recode(inputStream, outputStream);
            }
        }
    }
}

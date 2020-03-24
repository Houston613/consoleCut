package UtilityCut;

import java.io.*;

public class Cutter {
    private boolean symbol;
    //если флаг -w - true, то работа посимвольно
    private boolean isEnd;
    private boolean isStart;
    private int opening;
    private int ending;

//вроде неправильно создан конструктор. но как мне всякие isEnd объявить, если их значения в лаунчере задаются?
    public int checker(int length,boolean check){
        int start;
        int end;
        if ((isEnd && isStart) && (opening < length)){
            start = opening;
                if (ending < length)
                    end = ending;
                else
                    end = length-1;
        } else if ((isEnd && !isStart)) {
            start = 0;
            if (ending < length)
                end = ending;
            else
                end = length-1;
        } else if ((!isEnd && isStart) && (opening < length)) {
            start = opening;
            end = length - 1;
        } else {
            end = -1;
            start =-1;
            //null я не могу нормально возвращать, но думаю и так сойдет
            //такой случай может быть только если границы меньше
            //всякие исключения еще "на подходе" отпадут и тут будут только числа
            //если нет границ, добавляю целую строку
        }
        if (check)
            return start;
        else return end;
    }

    public String cutting(String lines) {
        //значения начала и конца могут не подаваться в метод, поэтому нужно записать их вот так
        StringBuilder line = new StringBuilder();
        int length;
        if (symbol) {
            length = lines.length();
            if ((checker(length,true)==-1)||(checker(length,false)==-1))
                line.append(lines);
            else
            line.append(lines,checker(length,true),checker(length,false));
            //append сказал что можно без цикла, а я и не против
        } else {
            String[] listOfWords = lines.split(" ");
            length = listOfWords.length;
            while (checker(length,true) >= checker(length,false)) {
                line.append(listOfWords[checker(length,false)]);
                length--;
                //добавяю с конца, checker(length,false) будет каждый раз уменьшаться
            }
        }
        return line.toString();
    }

    public void recode(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)){
            PrintStream out = new PrintStream(new File("output.txt"))
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                BufferedReader bufRead = new BufferedReader(reader);
                //вот тут неймы или выход
                BufferedWriter bufWrite = new BufferedWriter(writer);
                String buf = bufRead.readLine();
                while (buf != null) {
                    bufWrite.write(cutting(buf));
                    bufWrite.newLine();
                    //мне же нужно ето или он автоматом перехдит?
                    //убрал лист, понял что есть же writer
                    buf = bufRead.readLine();
                }
            }
        }
    }

    public void recode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                recode(inputStream, outputStream);
            }
        }
    }
}

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

    public String cutting(String lines, boolean symbol, boolean isEnd, boolean isStart){
        int start;
        int end;
        //значения начала и конца могут не подаваться в метод, поэтому нужно записать их вот так
        StringBuilder line = new StringBuilder();
        if (symbol){
            if ((isEnd&&isStart)&&(opening<line.length())&&(ending<line.length())){
                //если есть начало и конец
                start = opening;
                end = ending;
                }
            else if ((isEnd&&!isStart)&&(ending<line.length())){
                //только конец
                start = 0;
                end = ending;
            }
            else if ((!isEnd&&isStart)&&(opening<line.length())){
                //только начало
                start = opening;
                end = lines.length()-1;
            }
            else return null; //ничео не возвращаем если не попали в нужное условие
            //или нужно пустую строку ретернить?
            line.append(lines,start,end);
            //append сказал что можно без цикла, а я и не против
        }
        else{
            String[] listOfWords = lines.split(" ");
            //теперь не по буквам, а по словам
            if ((isEnd&&isStart)&&(opening< listOfWords.length)&&(ending<listOfWords.length)){
                start = opening;
                end = ending;
            }
            else if ((isEnd&&!isStart)&&(ending<listOfWords.length)){
                start = 0;
                end = ending;
            }
            else if ((!isEnd&&isStart)&&(opening<listOfWords.length)){
                start = opening;
                end = lines.length()-1;
            }
            else return null;
            //для того чтобы избежать копипасты я в if объявляю значения начала и конца, а потом просто по ним двигаюсь
            while (start>=end){
                line.append(listOfWords[start]);
                start++;
            }
        }
        return line.toString();
    }

    //текст читается по строкам, поэтому создаю лист строк
    public ArrayList<String> recode(InputStream in, OutputStream out,String inputName) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in, charsetInput)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out, charsetOutput)) {
                ArrayList<String> text = new ArrayList<>();
                BufferedReader bufRead = new BufferedReader(reader);
                String buf = bufRead.readLine();
                while (buf != null) {
                    text.add(cutting(buf, symbol, isEnd, isStart));
                    buf = bufRead.readLine();
                }
                return text;
            }
        }
    }
    public int recode(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return recode(inputStream, outputStream);
            }
        }
    }

}

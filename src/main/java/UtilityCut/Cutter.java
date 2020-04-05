package UtilityCut;


public class Cutter {
    private int opening;
    private int ending;
    private boolean isEnd;
    private boolean isStart;
    private boolean symbol;

    public Cutter(int opening,int ending,boolean isEnd,boolean isStart,boolean symbol) {
        this.symbol = symbol;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.ending = ending;
        this.opening = opening;
    }
    public int checker(int length,boolean check){
        int start;
        int end;
        if ((isEnd && isStart) && (opening < length)){
            start = opening-1;
                if (ending < length)
                    end = ending-1;
                else
                    end = length-1;
        } else if ((isEnd && !isStart)) {
            start = 0;
            if (ending < length)
                end = ending-1;
            else
                end = length-1;
        } else if ((!isEnd && isStart) && (opening < length)) {
            start = opening-1;
            end = length - 1;
        } else {
            end = -1;
            start = -1;
            //null я не могу нормально возвращать, но думаю и так сойдет
            //такой случай может быть только если границы меньше
            //всякие исключения еще "на подходе" отпадут и тут будут только числа
            //если нет границ, добавляю целую строку
        }
        if (check)
            return start;
        else return end;
    }

    public  String cutting(String lines) {
        StringBuilder line = new StringBuilder();
        int length = lines.length();
        if ((checker(length, true) == -1) && (checker(length, false) == -1))
            line.append(lines);
        else {
            if (symbol)
                line.append(lines,checker(length,true),checker(length,false));
                //append сказал что можно без цикла, а я и не против
            else {
                String[] listOfWords = lines.split(" ");
                length = listOfWords.length;
                for (int i = checker(length,true);i<=checker(length,false);i++){
                    line.append(listOfWords[i]);
                    if (i!=checker(length,false))
                        line.append(" ");
                }
            }
        }
        return line.toString();
    }
}

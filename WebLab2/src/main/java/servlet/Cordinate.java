package servlet;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Cordinate {
    private double x;
    private double y;
    private double r;
    private String requestTime;
    private long executionTime;
    private boolean correct;

    Cordinate(double x, double y, double r, String requestTime, long executionTime, boolean correct) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.requestTime = requestTime;
        this.executionTime = executionTime;
        this.correct = correct;
    }

    public String editOutput(Double n){
        DecimalFormat df = new DecimalFormat("###.##");
        DecimalFormat kk = new DecimalFormat("###.#");
        kk.setRoundingMode(RoundingMode.CEILING);
        df.setRoundingMode(RoundingMode.CEILING);
        if (Math.abs(n) >= 1000000000000000.0){ return String.valueOf(n).substring( 0, 3) + "�";}
        else if (Math.abs(n) >= 1000000000000.0){ return kk.format(n / 1000000000000.0) + "k^4";}
        else if (Math.abs(n) >= 1000000000) {return kk.format(n / 1000000000) + "kkk";}
        else if (Math.abs(n) >= 1000000) { return kk.format(n / 1000000) + "kk";}
        else if (Math.abs(n) >= 1000){ return df.format(n / 1000) + "k";}
        else { return df.format(n) + "";}
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public double getExecutionTime() {
        return executionTime/1000000.0;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getCorrectWords() {
        if (correct){
            return "<span style='color: green'>True</span>";
        }
        return "<span style='color: red'>False</span>";
    }
}
package dmitriev.tasksForPerfomance;

import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FirstTask {

    int percentile;
    double median;
    double average;
    int max;
    int min;
    String openedFilePath;
    ArrayList<Integer> digits;

    {
        digits = new ArrayList<>();
    }
    public void parseFileFor1stTask(){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Выбрать файл задания");
        try {
            FileReader fr = new FileReader(fileopen.getSelectedFile().getAbsolutePath());
            openedFilePath = fileopen.getSelectedFile().getAbsolutePath();
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                digits.add(Integer.parseInt(scan.nextLine()));
                //System.out.println(Integer.parseInt(scan.nextLine()));
            }
            fr.close();
        } catch (Exception exc) {
            System.out.println("Что-то с файлом было не так");
        }
    }
    public void calc90Percentile(){
       percentile=digits.get(Math.round((90.0f/100.0f*digits.size()))-1);
    }
    public void calcMedian(){
       if(digits.size()%2==0){
           median=(digits.get(digits.size()/2)+digits.get((digits.size()/2)-1))/2;
       }else{
           median=digits.get((digits.size()/2));
       }
    }
    public void calcAverage(){
       int sum=0;
       for(int i=0;i<digits.size();i++){
           sum+=digits.get(i);
       }
       average=Double.valueOf(sum)/digits.size();
    }
    public void calcMaxMin(){
       Collections.sort(digits);
       min = digits.get(0);
       max = digits.get(digits.size()-1);
    }
}

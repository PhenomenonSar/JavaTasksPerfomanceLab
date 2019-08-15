package dmitriev.tasksForPerfomance;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class ThirdTask {
    ArrayList<PeriodAndQueue> list;
    String openedFilePath;
    {
        list = new ArrayList<>();
    }
    public void parseFileFor3rdTask(){
        JFileChooser fileopen = new JFileChooser();
        fileopen.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        int ret = fileopen.showDialog(null, "Выбрать папку с файлами");
        openedFilePath = fileopen.getSelectedFile().getAbsolutePath();
        try {
            File[] files = fileopen.getSelectedFile().listFiles();
            for (int i=0;i<files.length;i++){
            FileReader fr = new FileReader(files[i].getAbsolutePath());
            Scanner scan = new Scanner(fr);
            int curPeriod=0;
            while (scan.hasNextLine()) {
                String[] keyAndValue = scan.nextLine().split("=");
                String period = keyAndValue[0].trim();
                double countQueue = Double.parseDouble(keyAndValue[1].trim());
                if(i>0){
                    list.get(curPeriod).queue+=countQueue;
                }else {
                    list.add(curPeriod,new PeriodAndQueue(period,countQueue));
                }
                curPeriod++;
            }
            fr.close();
            }
        } catch (Exception exc) {
            System.out.println("Что-то с файлом было не так");
        }
    }
    public Comparator<PeriodAndQueue> queueComparator = new Comparator<PeriodAndQueue>() {
        public int compare(PeriodAndQueue m1, PeriodAndQueue m2) {
            if(m1.getCurQueue()<m2.getCurQueue())
                return -1;
            else if(m1.getCurQueue()>m2.getCurQueue())
                return 1;
            return 0;
        }
    };
    public void sortList(){
        Collections.sort(list,queueComparator);
    }
    public PeriodAndQueue result(){
        return list.get(list.size()-1);
    }

}

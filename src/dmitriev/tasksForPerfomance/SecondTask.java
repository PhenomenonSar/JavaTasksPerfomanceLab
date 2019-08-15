package dmitriev.tasksForPerfomance;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Scanner;

public class SecondTask {
    Point [] points;
    Line [] lines;
    Polygon quadrangle;
    String openedFilePath;

    {
        points = new Point[4];
        lines = new Line[4];
    }
    public Point scanPointFromLine(String[] lineParts){
        int x,y;
        x = Integer.parseInt(lineParts[1].split("<")[0].trim());
        y = Integer.parseInt(lineParts[2].trim());
        return new Point(x,y);
    }
    public Point scanPointFromLine(String linePart){
        int x,y;
        String[] lineParts=linePart.split(">");
        x = Integer.parseInt(lineParts[1].split("<")[0].trim());
        y = Integer.parseInt(lineParts[2].trim());
        return new Point(x,y);
    }
    public void parseFileFor2ndTask(){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Выбрать файл задания");
        try {
            FileReader fr = new FileReader(fileopen.getSelectedFile().getAbsolutePath());
            openedFilePath = fileopen.getSelectedFile().getAbsolutePath();
            Scanner scan = new Scanner(fr);
            int numberPoint;
            while (scan.hasNextLine()) {
                String [] inputLine = scan.nextLine().split(">");
                numberPoint=Character.getNumericValue(inputLine[0].charAt(inputLine[0].length()-1));
                points[numberPoint-1]=scanPointFromLine(inputLine);
            }
            fr.close();
        } catch (Exception exc) {
            System.out.println("Что-то с файлом было не так");
        }
    }
    public void setLines(){
        lines[0]=new Line(points[0],points[1]);
        lines[1]=new Line(points[1],points[2]);
        lines[2]=new Line(points[2],points[3]);
        lines[3]=new Line(points[3],points[0]);
        for (int i=0;i<lines.length;i++){
            System.out.print("k= "+lines[i].k);
            System.out.println("m= "+lines[i].m);
        }
    }
    public boolean isCorner(Point a){
        for (int i=0;i<points.length;i++){
            if(points[i].equals(a)){
                return true;
            }
        }
        return false;
    }
    public boolean onLine(Point a){
        for(int i=0;i<lines.length;i++){
            try{
                if(lines[i].x==a.x || lines[i].y==a.y){
                    return true;
                }
            }catch (Exception e){

            }
            if(a.y==Math.round(a.x*lines[i].k+lines[i].m)){
                return true;
            }
        }
        return false;
    }
    public void fillQuadrangle() {
        quadrangle = new Polygon();
        for (int i=0;i<points.length;i++){
            quadrangle.addPoint(points[i].x,points[i].y);
        }
    }
    public boolean insideQuadrangle(Point a){
        if(quadrangle.contains(a)){
            return true;
        }
        return false;
    }
}

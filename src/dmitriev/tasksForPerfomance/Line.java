package dmitriev.tasksForPerfomance;

import java.awt.*;

public class Line {
    int x;
    int y;
    double k;
    double m;

    public Line(Point a, Point b) {
        findIndexKIndexM(a,b);
    }
    public void findIndexKIndexM(Point a, Point b){
        if(a.x==b.x){
            x=a.x;
        }else if(a.y==b.y){
            y=a.y;
        }else {
        k=(b.y*1.0-a.y*1.0)/(b.x*1.0-a.x*1.0);
        m=a.y-k*a.x;
        }
    }
}

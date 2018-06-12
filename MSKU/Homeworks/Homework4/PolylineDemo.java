package demo;
import shapes.*;
import java.util.ArrayList;



public class PolylineDemo {
        public static void main(String[] args){
                Point p = new Point(1, 2);
                Point p1 = new Point(3, 4);
                Point p2 = new Point(5, 6);
                Point p3 = new Point(0, 0);
                Point p4 = new Point(2, 7);
                Point p5 = new Point(34, 9);
                Point p6 = new Point(3, 2);
                Line line = new Line(p4, p3);
                ArrayList<Point> list = new ArrayList<Point>();
                list.add(p);
                list.add(p1);
                list.add(p2);
                Polyline pointList = new Polyline(list);
                pointList.createPoint(p6);
                pointList.createLine(line);
                pointList.AddPointAccIndex(0, p5);
                pointList.RemovePoint(pointList.Size() - 1);
                pointList.printAll();
        }
}

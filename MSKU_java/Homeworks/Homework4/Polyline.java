package shapes;
import java.util.ArrayList;

public class Polyline {
        private ArrayList<Point> pointList;

        public Polyline(ArrayList<Point> pointList){
                this.pointList = pointList;
        }

        public void printAll(){
                int index = 0;
                for (Point point : pointList) {
                        System.out.println("Index(" + index + ")" + "\tx = " + point.GetX() + "\ty = " + point.GetY());
                        index++;
                }
        }

        public void createLine(Line line){
                pointList.add(line.GetStart());
                pointList.add(line.GetEnd());
        }


        public void AddPointAccIndex(int index, Point point){
                pointList.add(index, point);
        }
        public void createPoint(Point point){
                pointList.add(point);
        }
        public void RemovePoint(int index){
                pointList.remove(index);
        }

        public int Size(){
                return pointList.size();
        }
}

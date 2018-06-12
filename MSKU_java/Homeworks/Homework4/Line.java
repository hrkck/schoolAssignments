package shapes;

public class Line {
        private Point start;
        private Point end;

        public Line(Point start, Point end){
                this.start = start;
                this.end = end;
        }


        public Point GetStart(){
                return start;
        }
        public Point GetEnd(){
                return end;
        }

        public void printValues(){
                System.out.println("Start Point is: x=" + start.GetX() + ", y= " + start.GetY() + " End Point : x=" + end.GetX() + ", y=" + end.GetY());
        }
}

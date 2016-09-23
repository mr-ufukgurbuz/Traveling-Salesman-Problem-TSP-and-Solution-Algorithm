
package MyProject;
/**
 *
 * @author _SeriousBoy_
 */
import java.util.ArrayList;


public class TSP {
    
    static ArrayList <Point>originalPointList = new ArrayList<Point>();
    static ArrayList <Point>firstPointList = new ArrayList<Point>(); 
    static ArrayList <Point>pointList = new ArrayList<Point>();
    
    static Traverse best;
    Traverse newTraverse;
    
    double tmp;
    double decRate;
    
    public TSP(double tmp,double decRate){
        this.tmp = tmp;
        this.decRate = decRate;
    }
    
    public static Point getPoint(int index){
        return (Point)pointList.get(index);
    }
    
    public void repairMethod(Traverse simdikiTraverse){
       
        best = new Traverse(simdikiTraverse.tour);
        
        int i=1;
        while (tmp > 1) {
            i++;
            newTraverse = new Traverse(simdikiTraverse.tour);

            swapCities();
            
            double currentCost = simdikiTraverse.findDistance();
            double neighbourCost = newTraverse.findDistance();

            if (controlChange(currentCost, neighbourCost, tmp) > Math.random()) {
                simdikiTraverse = new Traverse(newTraverse.tour);
            }

            if (simdikiTraverse.findDistance() < best.findDistance()) {
                best = new Traverse(simdikiTraverse.tour);
            } 
            
            if(i % 10000 == 0){
                i=0;
                System.out.println("Current solution distance: " + simdikiTraverse.findDistance());
            }
            tmp *= 1-decRate;
        }
        
    }
     
    public void swapCities(){
        
            int pos1 = (int) (newTraverse.tour.size() * Math.random());
            int pos2 = (int) (newTraverse.tour.size() * Math.random());
            
            
            Point swap1 = (Point)newTraverse.tour.get(pos1);
            Point swap2 = (Point)newTraverse.tour.get(pos2);
            
            

            newTraverse.setPoint(pos2, swap1);
            newTraverse.setPoint(pos1, swap2);
    }
    
    public double controlChange(double cost, double newCost, double tmp) { 
        if (newCost < cost)
            return 1.0;   
        return Math.exp((cost - newCost) / tmp);
    }
    
}

class Point {
    int pointNo;
    int x;
    int y;
    
    //boolean condition = false;
    
    public Point(int pointNo, int x, int y){
        setPointNo(pointNo);
        setX(x);
        setY(y);
    }
    
    public void setPointNo(int pointNo){
        this.pointNo = pointNo;
    }
        
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    /*public void setCondition(){
        this.condition = true;
    }*/
    
    public int getPointNo(){
        return this.pointNo;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    /*public boolean getCondition(){
        return this.condition;
    }*/
    public String toString(){
        return pointNo+"";  //+ " : " + x + ", " + y
    }
}
            
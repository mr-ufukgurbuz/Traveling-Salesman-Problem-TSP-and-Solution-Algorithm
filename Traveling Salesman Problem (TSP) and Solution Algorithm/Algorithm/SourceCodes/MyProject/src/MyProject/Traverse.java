
package MyProject;

import java.util.ArrayList;
import java.util.Collections;

public class Traverse{

    ArrayList <Point>tour = new ArrayList<Point>();
    double dist = 0;
    
    public Traverse(){
        for (int i = 0; i < TSP.pointList.size(); i++) {
            tour.add(i,null);
        }
    }
    
    public static double fromDistanceTo(Point point1, Point point2){
        
        int xDist = Math.abs(point1.getX() - point2.getX());              // Distance between cities
        int yDist = Math.abs(point1.getY() - point2.getY());

        double dist = Math.sqrt( (xDist*xDist) + (yDist*yDist) );
        
        return dist;
    }

    public void setPoint(int tourPosition, Point point) {
        tour.set(tourPosition, point);
        dist = 0;
    }
    
    public long findDistance(){
        if (dist == 0) {
            double traverseDistance = 0;

            for (int index=0; index < tour.size(); index++) {

                Point fromPoint = (Point)tour.get(index);
                Point destPoint;

                if(index+1 < tour.size()){
                    destPoint = (Point)tour.get(index+1);
                }
                else{
                    destPoint = (Point)tour.get(0);
                }
                
                traverseDistance += Math.round(fromDistanceTo(fromPoint,destPoint));
            }
            dist = traverseDistance;
        }
        return Math.round(dist);
    }
    
    public Traverse(ArrayList tour){
        this.tour = (ArrayList) tour.clone();
    }
    
    public void createFirstTour() {
        for (int index = 0; index < TSP.pointList.size(); index++) {
          setPoint(index, TSP.getPoint(index));
        }  
    }
    
}
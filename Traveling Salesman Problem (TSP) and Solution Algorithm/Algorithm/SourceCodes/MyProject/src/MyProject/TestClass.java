
package MyProject;
/**
 *
 * @author _SeriousBoy_
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {
    static double tmp = 1000;	//1000
    static double decRate = 0.00001;      // 1'li 10000 6 tane 0.1(109000-5dk) ***** 2'li 6 tane 0.1(3000-20dk) 7 tane 0.1(2830-160dk) ***** 3'lu 1000 6 tane 0.1 (5.1m-11saat) 1000 7 tane 0.1 (5m-29saat)
          
    static String inputFile = "worldInput.txt";
    static String outputFile = "worldOutput.txt";
    
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        
        readFromFile(inputFile);        // Dosyadan data ceker.

        TSP.firstPointList=(ArrayList<Point>) TSP.originalPointList.clone();
        nearestOrderList();
 
        Traverse tour = new Traverse();
        tour.createFirstTour();

        System.out.println("\t<<< INITIAL SOLUTION DISTANCE >>>\n");
        
        for(int i=0; i<TSP.originalPointList.size(); i=i+10){//TSP.originalPointList.size()
            TSP.pointList.clear();
            
            TSP.firstPointList=(ArrayList<Point>) TSP.originalPointList.clone();
			
            nearestOrderList();                         // En yakini turlama teknigini uygular.
			
            Traverse tour2 = new Traverse();      // Suan sirasiyla gidilecek sekilde bir tur olusturdu.
            tour2.createFirstTour();   // Sonra bunlari karistirdi.
           	
            System.out.println(i+ "  Initial solution distance: " + tour.findDistance());
            System.out.println("-----------------------------------------------");
            
            if(tour2.findDistance()<tour.findDistance()){
                tour=tour2;
            }
            
        }
            System.out.println("\t<<< BEST INITIAL SOLUTION DISTANCE >>>\n");
            System.out.println("Best initial solution distance: " + tour.findDistance());
            System.out.println("-----------------------------------------------");
        //Graphic.showCities();
     
    long startTime = System.currentTimeMillis(); 
        
        TSP t = new TSP(tmp,decRate);
        System.out.println("\t<<< CURRENT SOLUTION DISTANCE >>>");
        t.repairMethod(tour);
        
        System.out.println("-----------------------------------------------");
        System.out.println("\t<<< BEST SOLUTION DISTANCE >>>\n");
        System.out.println("Best solution distance: " + t.best.findDistance());
        System.out.println("-----------------------------------------------");
        
    long endTime = System.currentTimeMillis();
        
    long estimatedTime = endTime - startTime; // Gecen sureyi milisaniye cinsinden elde ediyoruz.
        
        printTour(t); 
        writeToFile(outputFile,t);
        
        printTime(estimatedTime);
        
    }
    
    
    public static void printTime(long estimatedTime){
        float seconds = (float)estimatedTime/1000; // saniyeye cevirmek icin 1000'e boluyoruz.
        float minutes = (float) seconds/60;
        float hours = (float) seconds/3600;
        System.out.println("-----------------------------------------------");
        System.out.println("\n\t<<< Elapsed Time >>>");
        System.out.println("\nSeconds : " + seconds);
        System.out.println("Minutes : " + minutes);
        System.out.println("Hours   : " + hours);
        System.out.println("-----------------------------------------------");
    }
    
    public static void nearestOrderList() throws InterruptedException{
        int pos1 = (int) (TSP.firstPointList.size() * Math.random());
        Point point1 = (Point) TSP.firstPointList.get(pos1);
        System.out.println("Random Number: "+pos1);
        TSP.pointList.add(point1);
        TSP.firstPointList.remove(pos1);
		
        int times = TSP.firstPointList.size();
		
        for(int x=0; x<times; x++){
            double minDist = 1000000000;
            int index=1;

            for(int i=0; i < TSP.firstPointList.size(); i++){
                Point point2 = (Point) TSP.firstPointList.get(i);
                double newDist = Traverse.fromDistanceTo(point1,point2);
                if(newDist < minDist){
                    minDist = newDist;
                    index = i;
                }
                
            }
           //if(index < TSP.firstPointList.size()){
            point1 = (Point) TSP.firstPointList.get(index);
            TSP.pointList.add(point1);
            TSP.firstPointList.remove(index);
           //}
           //else{
           // System.out.println("Hata Index: " + index);
           // }
        }
		 
    }
    
    public static void readFromFile(String path) throws FileNotFoundException{
        File file = new File(path);
	
        if(!file.exists()){
            System.out.println("Not Found File !!!");
            System.exit(0);
        }
        
        Scanner input = new Scanner(file);
        
        while(input.hasNext()){
            int pointNo = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();
            Point point = new Point(pointNo,x,y);
            TSP.originalPointList.add(point);
        }
        input.close();
    }
    
    public static void writeToFile(String path,TSP t) throws FileNotFoundException{
        File file = new File(path);
        PrintWriter out = new PrintWriter(file);
        
        out.println(t.best.findDistance());
        for(int i=0; i < t.best.tour.size(); i++)
            out.println(t.best.tour.get(i));
        
        out.close();
    }
    
    public static void printTour(TSP t){
        System.out.println("\t<<< BEST SOLUTION TOUR >>>\n");
        
        System.out.println(t.best.findDistance());
        for(int i=0; i < t.best.tour.size(); i++)
            System.out.println(t.best.tour.get(i));
    }
 
}
    




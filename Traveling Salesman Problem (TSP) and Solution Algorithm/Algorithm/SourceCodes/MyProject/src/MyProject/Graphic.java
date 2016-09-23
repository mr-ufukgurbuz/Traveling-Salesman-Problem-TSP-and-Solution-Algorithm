
package MyProject;
/**
 *
 * @author _SeriousBoy_
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;                          // Imports
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Graphic extends Application {          // Shows points onto graphic
    static int x = 0;
    static int y = 0;
    
    @Override 
    public void start(Stage stage) {
        stage.setTitle("DISTRIBUTION of CITIES(Input 3)");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("X Coordinates");
         yAxis.setLabel("Y Coordinates");
        final ScatterChart<Number,Number> lineChart = new ScatterChart<Number,Number>(xAxis,yAxis);
       
        lineChart.setTitle("TSP Problem");
        
                          
        XYChart.Series series = new XYChart.Series();
        series.setName("Cities");
        
        ArrayList <Point>tour = (ArrayList)TSP.pointList;
        for(int i=0; i<tour.size(); i++){
            int x = tour.get(i).x;
            int y = tour.get(i).y;
            series.getData().add(new XYChart.Data(x, y));
        }

        Scene scene  = new Scene(lineChart,1500,700);    
        
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
    
    public static void showCities(){
        launch();     
    }
    
}

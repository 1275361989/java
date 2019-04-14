package cw1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.FileNotFoundException;


public class ElevationPlot  extends Application  {
	  Track try1=new Track();
	
    @Override public void start(Stage stage)throws FileNotFoundException { 
    	try1.readFile("../data/walk.csv");
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Distance (m)");
        yAxis.setLabel("Elevation (m)");
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Elevation Plot");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("walk.csv");
        //populating the series with data
        lineChart.setCreateSymbols(false);
        double S=0;
        for(int i=0;i<try1.size()-1;i++) {
        	S+= try1.get(i).greatCircleDistance(try1.get(i), try1.get(i+1));
        	series.getData().add(new XYChart.Data(S, try1.get(i).getElevation()));
        }
        
        
        
        Scene scene  = new Scene(lineChart,1000,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}

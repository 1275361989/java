package cw1;

import java.io.FileNotFoundException;
public class TrackInfo {

	public static void main(String[] args) throws GPSException, FileNotFoundException{
		try {
			Track track1=new Track();
			track1.readFile(args[0]);
			System.out.println("The number of the points: "+track1.size());
			System.out.println("The lowest point: "+track1.lowestPoint().toString());
			System.out.println("The highest point: "+track1.highestPoint().toString());
			System.out.printf("The total distance: %.5f km\n",track1.totalDistance()/1000);
			System.out.printf("The average speed: %.5f m/s\n",track1.averageSpeed());
		}
		catch(GPSException o){
			System.out.println(o.toString());
    		System.exit(1);
		}
		
	}
}

package cw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
public class Track{
	private ArrayList<Point> arr=new ArrayList<>();//use ArrayList to define a dynamic array
	
	public Track(){	//to define a default constructor
		
	}
	public void  readFile(String file) throws FileNotFoundException, GPSException{
		File source=new File(file);
		if(!source.exists()) {									//if file does not exist,throws the exception
			throw new FileNotFoundException(" File does not exist");
		}
		Scanner inputFile =new Scanner(source);
		String[] temArr= new String[4];							//define a temporary array, to store the data which is separated into 4 parts 
		inputFile.nextLine();									//skip the title in .csv file
		arr.clear();											// clear the arrayList completely 
		while(inputFile.hasNext()) {							//if the file has the next line, keeping reading
			temArr=inputFile.nextLine().split(",");				// separator is ",", by which each line data is divided into 4 parts
			if(temArr.length!=4)								//if the length of data is not 4, throw the exception
				throw new GPSException("data error  in file");
			for(int i=0;i<4;i++)
			{
				if(temArr[i]==null)   							//if each array has null, throws the exceptions
					throw new GPSException("The file is empty");
			}
			arr.add(new Point(ZonedDateTime.parse(temArr[0]),Double.parseDouble(temArr[1]),Double.parseDouble(temArr[2]),Double.parseDouble(temArr[3])));
		}														//put the data into the ArrayList, change the format into 
		inputFile.close();										//close the input stream
		
	}
	public void add(Point newone){								//add the new Point type various
		arr.add(newone);
	}
	public int size() {											//return the size of the track
		return arr.size();
	}
	public Point get(int index) throws GPSException {			//return the needed point in the track
		if(arr.size()<=index||index<0||arr.size()==0) {			//if the total amount of point is not enough, throw the exception 
			throw new GPSException("the search is out of range");
		}
		return arr.get(index);
		
	}
	public Point lowestPoint() throws GPSException {			
		if(arr.size()==0) {										//if the total amount of point is not enough, throw the exception
			throw new GPSException("the track is null");
		}
		double lowest=arr.get(0).getElevation();				//assure the first one is the lowest point
		int index=0;
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getElevation()<lowest) {				//if each point is lower than the lowest point, replace it
				lowest=arr.get(i).getElevation();
				index=i;
			}
		}
		return arr.get(index);
	}
	public Point highestPoint() throws GPSException{
		if(arr.size()==0) {										//if the total amount of point is not enough, throw the exception
			throw new GPSException("the track is null");
		}
		double highest=arr.get(0).getElevation();				//assure the first one is the highest point
		int index=0;
		for(int i=0;i<arr.size();i++) {
			if(arr.get(i).getElevation()>highest) {				//if each point is higher than the lowest point, replace it
				highest=arr.get(i).getElevation();
				index=i;
			}
		}
		return arr.get(index);
		
	}
	public double totalDistance() throws GPSException{
		double distance=0;							
		if(arr.size()<=1)									//if the total amount of point is not enough, throw the exception
			throw new GPSException("no enough point");
		for(int i=0;i<arr.size()-1;i++) {					// call the function to calculate the total distance
			distance += Point.greatCircleDistance(arr.get(i), arr.get(i+1));
		}
		return distance;
	}
	public double averageSpeed() throws GPSException{
		if(arr.size()<=1)									//if the total amount of point is not enough, throw the exception
			throw new GPSException("no enough point");
		double avsp =totalDistance()/ChronoUnit.SECONDS.between(arr.get(0).getTime(), arr.get(arr.size()-1).getTime());
		return avsp;										//average speed is equal the total distance / total time
	}
	
}

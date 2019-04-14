package cw1;
import java.time.ZonedDateTime;
import static java.lang.Math.*;



/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & YOUR NAME
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  // TODO: Define fields for time, longitude, latitude and elevation
  private ZonedDateTime time;
  private double longitude;
  private double latitude;
  private double elevation;
  // TODO: Define a constructor
  public Point(){
	//the default constructor
  }
  public Point(ZonedDateTime time,double longitude,double latitude,double elevation)throws GPSException{
	  if(longitude>MAX_LONGITUDE||longitude<MIN_LONGITUDE)//if the longitude entered is out of range, throw the exception
		  throw new GPSException("the longitude entered is out of range");
	  else
		  this.longitude=longitude;
	  if(latitude>MAX_LATITUDE||latitude<MIN_LATITUDE)//if the latitude entered is out of range, throw the exception
		  throw new GPSException("the latitude entered is out of range");
	  else
		  this.latitude=latitude;
	  this.elevation=elevation;//assign the value to elevation and time
	  this.time=time;
  }
  // TODO: Define getters for the fields
  //return the time,longitude,latitude and elevation 
  public ZonedDateTime getTime() {
	  return time;
  }
  public double getLongitude() {
	  return longitude;
  }
  public double getLatitude() {
	  return latitude;
  }
  public double getElevation() {
	  return elevation;
  }
  // TODO: Define a toString() method that meets requirements
  public String toString() {
	  String message=String.format("(%.5f, %.5f), %.1f m",longitude,latitude,elevation);//give a good format to return
	  return message;
  }
  // Do not alter anything beneath this comment

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}





package at.htl.smartbot;

import java.util.ArrayList;

import at.htl.geometrics.*;

/**
 * Represent a track of our SmartRobot stored as a ArrayList of navigation points
 * 
 * @author Jakob Ecker
 * @version 1.0
 */
public class Track {
	
	private ArrayList<Point> track = new ArrayList<Point>();
	
	/**
	 * Constructs a new empty Track - object.
	 */
	public Track(){	}
	
	/**
	 * Constructs a new Track - object with a list of navigation points
	 * @param navPoints ArrayList of navigation {@link Point}s.
	 */
	public Track(ArrayList<Point> navPoints){
		track = navPoints;
	}
	
	/**
	 * Returns the last position of the track.
	 * @return Last position.
	 */
	public Point getLastPosition(){
		return track.get(track.size()-1);
	}
	
	/**
	 * Returns the first position of the track.
	 * @return Starting position.
	 */
	public Point getFirstPosition(){
		return track.get(0);
	}
	
	/**
	 * Returns the position at an certain index.
	 * @param index The index of the position.
	 * @return the position of the track at an certain index.
	 */
	public Point getPosition(int index){
		return track.get(index);
	}
	
	/**
	 * Add a new navigation {@link Point} to the track, added as the last position.
	 * @param navigationPoint Point to be added.
	 */
	public void addNavPoint(Point navigationPoint){
		track.add(navigationPoint);
	}
	
	/**
	 * Returns all track points.
	 * @return All track points.
	 */
	public ArrayList<Point> getAllPositions(){
		return track;
	}
}

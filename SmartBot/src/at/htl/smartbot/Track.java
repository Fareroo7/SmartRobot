package at.htl.smartbot;

import java.util.ArrayList;

import at.htl.geometrics.*;

/**
 * Represent a track of our smartbot stored as a ArrayList of navigation points
 * @author Jakob Ecker
 * @author Domink Simon
 *
 */
public class Track {
	
	private ArrayList<Point> track = new ArrayList<Point>();
	
	/**
	 * Constructs a new empty Track-Object
	 */
	public Track(){	}
	
	/**
	 * Constructs a new Track-Object with a list of navigation points
	 * @param navPoints
	 */
	public Track(ArrayList<Point> navPoints){
		track = navPoints;
	}
	
	/**
	 * 
	 * @return the last position of the track
	 */
	public Point getLastPosition(){
		return track.get(track.size()-1);
	}
	
	/**
	 * 
	 * @return the starting position of the track
	 */
	public Point getFirstPosition(){
		return track.get(0);
	}
	
	/**
	 * 
	 * @param index
	 * @return the position of the track at index
	 */
	public Point getPosition(int index){
		return track.get(index);
	}
	
	/**
	 * add a new navigation point to the track, added as the last position
	 * @param navigationPoint
	 */
	public void addNavPoint(Point navigationPoint){
		track.add(navigationPoint);
	}
	
	/**
	 * Getter
	 * @return
	 */
	public ArrayList<Point> getAllPositions(){
		return track;
	}
}

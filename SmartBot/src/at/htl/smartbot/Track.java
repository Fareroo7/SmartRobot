package at.htl.smartbot;

import java.util.ArrayList;
import java.util.Arrays;

import at.htl.geometrics.*;

public class Track {
	
	private ArrayList<Point> track = new ArrayList<Point>();
	
	public Track(){
		
	}
	
	public Track(ArrayList<Point> positions){
		track = positions;
	}
	
	public Point getLastPosition(){
		return track.get(track.size()-1);
	}
	
	public Point getFirstPosition(){
		return track.get(0);
	}
	
	public Point getPosition(int index){
		return track.get(index);
	}
	
	public void addNavPoint(Point navigationPoint){
		track.add(navigationPoint);
	}
	
	public ArrayList<Point> getAllPositions(){
		return track;
	}
}

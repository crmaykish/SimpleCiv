package maykish.colin.simpleciv;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.units.Unit;
import maykish.colin.simpleciv.util.Point;

public class Player {

	private String name;
	public Color color;
	private ArrayList<Unit> units;
	
	private ArrayList<Point> discoveredTiles;
	
	public Player(String name, Color color){
		this.name = name;
		this.color = color;
		
		units = new ArrayList<Unit>();
		
		discoveredTiles = new ArrayList<Point>();
	}
	
	public void addUnit(Unit u){
		units.add(u);
	}
	
	public void removeUnit(Unit u){
		for (int i=0; i< units.size(); i++){
			if (units.get(i).equals(u)){
				units.remove(i);
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
	public int getUnitCount(){
		return units.size();
	}
	
	public boolean allUnitsMoved(){
		for (Unit u : units){
			if(u.currentMoves != 0){
				return false;
			}
		}
		
		return true;
	}
	
	public void resetUnitMovement(){
		for (Unit u : units){
			u.currentMoves = u.maxMoves;
		}
	}
	
	public ArrayList<Point> getDiscoveredTiles(){
		return discoveredTiles;
	}
	
	public void addDiscoveredTile(Point p){
		discoveredTiles.add(p);
	}
	
	public ArrayList<Point> getVisibleTiles(){
		ArrayList<Point> points = new ArrayList<Point>();
		for (Unit u : units){
			
			int x = u.location.x;
			int y = u.location.y;
			int range = u.getLineOfSight();
			
			for (int i = y - range; i <= y + range; i++){
				for (int j = x - range; j <= x + range; j++){
					if (i >= 0 && j >= 0){
						points.add(new Point(j, i));
					}
				}
			}
			
//			points.add(u.location);
		}
		return points;
	}
}

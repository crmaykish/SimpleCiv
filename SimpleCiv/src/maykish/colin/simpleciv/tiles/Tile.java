package maykish.colin.simpleciv.tiles;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.HasTexture;
import maykish.colin.simpleciv.units.Camp;
import maykish.colin.simpleciv.units.Unit;
import maykish.colin.simpleciv.util.Point;

public abstract class Tile implements HasTexture {

	public Point location;
	protected String textureName;

	protected boolean visible;
	protected boolean discovered;
	
	protected LinkedList<Unit> units;
	
	protected Camp camp = null;

	public Tile(Point location) {
		this.location = location;
		
		visible = false;
		discovered = false;
		
		units = new LinkedList<Unit>();
	}
	
	public abstract Color getColor();

	@Override
	public String getTextureName() {
		return textureName;
	}

	public boolean hasUnits(){
		if (units.size() > 0){
			return true;
		}
		return false;
	}
	
	public int getUnitCount(){
		return units.size();
	}
	
	public Unit getTopUnit(){
		return units.peekFirst();
	}
	
	public void addUnit(Unit u) {
		units.offerFirst(u);
	}

	public void removeTopUnit(){
		units.pollFirst();
	}
	
	// cycle the top unit of the tile
	public void nextUnit(){
		units.offerLast(units.pollFirst());
	}
	
	public void addCamp(Camp camp){
		this.camp = camp;
	}
	
	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean set){
		visible = set;
	}
}

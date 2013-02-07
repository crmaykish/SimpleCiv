package maykish.colin.simpleciv.units;

import maykish.colin.simpleciv.HasTexture;
import maykish.colin.simpleciv.Player;
import maykish.colin.simpleciv.util.Point;

public abstract class Unit implements HasTexture{

	public Player owner;
	
	protected String textureName;
	public Point location;
	
	public int maxMoves;
	public int currentMoves;
	
	public int maxHealth;
	public int currentHealth;
	
	public int attack;
	public int defense;
	
	protected int lineOfSight;
	
	public Unit(Player owner, Point location){
		this.owner = owner;
		this.location = location;
		
		setStats();
	}
	
	protected abstract void setStats();
	
	@Override
	public String getTextureName() {
		return textureName;
	}
	
	public String getUnitStatus(){
		return "HP: "
				+ currentHealth + "/" 
				+ maxHealth 
				+ "\n"
				+ "Moves: "
				+ currentMoves + "/"
				+ maxMoves;
	}

	public int getLineOfSight() {
		return lineOfSight;
	}

	public void setLineOfSight(int lineOfSight) {
		this.lineOfSight = lineOfSight;
	}
	
}

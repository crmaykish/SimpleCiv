package maykish.colin.simpleciv.units;

import maykish.colin.simpleciv.Player;
import maykish.colin.simpleciv.util.Point;

public class WarriorUnit extends Unit{

	public WarriorUnit(Player owner, Point location) {
		super(owner, location);
		textureName = "warrior";
	}

	@Override
	protected void setStats() {
		maxMoves = 1;
		currentMoves = 1;
		
		maxHealth = 10;
		currentHealth = 10;
		
		attack = 10;
		defense = 6;
		
		lineOfSight = 2;
	}

}

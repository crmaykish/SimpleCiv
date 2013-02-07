package maykish.colin.simpleciv.units;

import maykish.colin.simpleciv.Player;
import maykish.colin.simpleciv.util.Point;

public class Camp extends Unit {

	protected int population;

	public Camp(Player owner, Point location) {
		super(owner, location);
		textureName = "camp";
	}

	Player owner;
	private int maxHealth;
	private int currentHealth;

	@Override
	protected void setStats() {
		maxMoves = 0;
		currentMoves = 0;

		maxHealth = 50;
		currentHealth = 50;

		attack = 0;
		defense = 10;

		lineOfSight = 2;

		population = 1;
	}

	@Override
	public String getUnitStatus() {
		return "Pop: " + population;
	}

}

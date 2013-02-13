package maykish.colin.simpleciv;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.map.Map;
import maykish.colin.simpleciv.tiles.Tile;
import maykish.colin.simpleciv.units.*;
import maykish.colin.simpleciv.util.Point;

public class GamePlay {

	private int daysPassed = 1;
	
	private Player currentPlayer;
	
	public Player player1;
	public Player player2;
	
	Map map;
	
	public GamePlay(Map map){
		
		this.map = map;
		
		initPlayers();
		
		createWarrior(player1, new Point(2, 2));
		createWarrior(player1, new Point(2, 1));
		createCamp(player1, new Point(3, 3));
		createWarrior(player2, new Point(6, 2));
	}
	
	private void initPlayers(){
		player1 = new Player("Colin", Color.RED);
		player2 = new Player("Loser", Color.GREEN);
		
		currentPlayer = player1;
	}
	
	 private void createWarrior(Player owner, Point location){
		WarriorUnit warrior = new WarriorUnit(owner, location);
		
		// add it to player's list
		owner.addUnit(warrior);
		// add it to tile
		map.getTileAt(location).addUnit(warrior);
	}
	
	 private void createCamp(Player owner, Point location){
		 Camp camp = new Camp(owner, location);
		 owner.addUnit(camp);
		 map.getTileAt(location).addUnit(camp);
		 map.getTileAt(location).addCamp(camp);
	 }
	 
	// TODO: this will remove the top unit from the tile where the unit is...
	// that's not necessarily the actual unit. keep that in mind
	// make this more bulletproof at some point
	private void removeUnit(Unit u){
		// remove it from player's list
		u.owner.removeUnit(u);
		
		// remove it from tile
		map.getTileAt(u.location).removeTopUnit();
	}
	
	public void update(){
		
		map.setAllTilesToInvisible();
		
		for (Point p : player1.getVisibleTiles()){
			map.setTileVisible(p, true);
		}
		
		if (player1.allUnitsMoved()){
			currentPlayer = player2;
			
//			if (player2.allUnitsMoved()){
				currentPlayer = player1;
				
				// turn is over
				player1.resetUnitMovement();
				player2.resetUnitMovement();
//			}
		}
	}
	
	public void tileTouched(Point tileCoords){
	
		// TODO: this logic needs some serious cleaning
		// if selected tile has units...
				if (map.isTileSelected() && map.getTileAt(map.getSelectedCoords()).hasUnits()){
					// if the clicked tile is not the already selected tile...
					if (map.getSelectedCoords().x != tileCoords.x || map.getSelectedCoords().y != tileCoords.y){
						
						// if the units have different players
						if (map.getTileAt(tileCoords).hasUnits() && !map.getTileAt(tileCoords).getTopUnit().owner.equals(map.getTileAt(map.getSelectedCoords()).getTopUnit().owner)){
							// attack
							battle(map.getTileAt(map.getSelectedCoords()), map.getTileAt(tileCoords));
							map.clearSelectedTile();
						} else {
							// move the top unit to the clicked tile
							moveUnit(map.getSelectedCoords(), tileCoords);
							map.clearSelectedTile();
						}
						return;
					}
				}
				
				// if the selected square has a unit that belongs to the current player
				if (map.getTileAt(tileCoords).hasUnits() && map.getTileAt(tileCoords).getTopUnit().owner.equals(currentPlayer)) {
					if (map.getSelectedCoords().equals(tileCoords)){
						// if it's already the selected tile, cycle the units
						map.getTileAt(tileCoords).nextUnit();
					} else{
						// otherwise, just select it
						
						map.setSelectedTile(tileCoords);
						
					}
					
				} else {
					map.clearSelectedTile();
				}

				return;
		
	}
	
	public void moveUnit(Point from, Point to){
		// TODO: run some checks to make sure it's a valid move
		// also, clean this crap up....
		if (map.getTileAt(from).getTopUnit().currentMoves > 0 && isValidMove(from, to)){
			// change unit's location
			map.getTileAt(from).getTopUnit().location = to;
			// copy unit to new tile
			map.getTileAt(to).addUnit(map.getTileAt(from).getTopUnit());
			// remove unit from original tile
			map.getTileAt(from).removeTopUnit();
			// decrement currentMoves
			map.getTileAt(to).getTopUnit().currentMoves--;
		}
	}
	
	private boolean isValidMove(Point start, Point end){
		boolean validOnMap = map.getValidMoves(start).contains(end);
		boolean playersTurn = (map.getTileAt(start).getTopUnit().owner == currentPlayer) ? true : false;
		
		return validOnMap && playersTurn;
	}
	
	public void battle(Tile attacker, Tile defender){
		
		if (attacker.getTopUnit().currentMoves > 0 && isValidMove(attacker.location, defender.location)){
		
			int attackDamage = attacker.getTopUnit().attack - defender.getTopUnit().defense;
			int defenseDamage = defender.getTopUnit().attack - attacker.getTopUnit().defense;
			
			// attacking takes up all moves
			attacker.getTopUnit().currentMoves = 0;
			
			// both units technically die, give the one who has more hitpoints (even if negative), the victory
			if (attackDamage >= defender.getTopUnit().currentHealth && defenseDamage >= attacker.getTopUnit().currentHealth){
				if (attacker.getTopUnit().currentHealth - defenseDamage > defender.getTopUnit().currentHealth - attackDamage){
					//attacker survives
					attacker.getTopUnit().currentHealth = 1;
					defender.getTopUnit().currentHealth = 0;
				} else {
					// defender survives
					attacker.getTopUnit().currentHealth = 0;
					defender.getTopUnit().currentHealth = 1;
				}
				
			}
			// if there's only one possible death
			else {
				// attacker does damage
				defender.getTopUnit().currentHealth -= attackDamage;
				
				// defender retaliates
				attacker.getTopUnit().currentHealth -= defenseDamage;
			}
			
			// check defender death
			if (defender.getTopUnit().currentHealth <= 0){
				//defender died
				removeUnit(defender.getTopUnit());
			}
	
			// check attacker death
			if (attacker.getTopUnit().currentHealth <= 0){
				// attacker died
				removeUnit(attacker.getTopUnit());
			}
		}
	}
	
	// return the necessary info so the renderer can display
	// get scores, player stats, etc...
	public int getTurnsPassed(){
		return daysPassed;
	}
	
	public String getCurrentPlayerName(){
		return currentPlayer.getName();
	}
}

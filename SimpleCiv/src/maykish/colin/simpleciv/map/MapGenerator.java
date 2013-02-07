package maykish.colin.simpleciv.map;

import maykish.colin.simpleciv.tiles.Tile;

public interface MapGenerator {

	public Tile[][] generateMap(int width, int height);
	
}

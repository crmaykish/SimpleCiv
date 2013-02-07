package maykish.colin.simpleciv.map;

import maykish.colin.simpleciv.tiles.*;
import maykish.colin.simpleciv.util.Point;

public class PerlinMapGenerator implements MapGenerator{

	@Override
	public Tile[][] generateMap(int width, int height) {
		
		Tile[][] map = new Tile[width][height];

		double array[][] = PerlinNoise.GeneratePerlinNoise(width, height, 6);

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int r = (int) (array[i][j] * 1000);
				if (r < 475) {
					map[i][j] = new DeepWaterTile(new Point(i, j));
				} else if (r < 525) {
					map[i][j] = new WaterTile(new Point(i, j));
				} else if (r < 625) {
					map[i][j] = new GrassTile(new Point(i, j));
				} else if (r < 725) {
					map[i][j] = new HillsTile(new Point(i, j));
				} else {
					map[i][j] = new MountainsTile(new Point(i, j));
				}
			}
		}
		return map;
	}
}

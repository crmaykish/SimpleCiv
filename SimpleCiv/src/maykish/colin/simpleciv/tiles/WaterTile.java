package maykish.colin.simpleciv.tiles;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.util.Point;

public class WaterTile extends Tile {

	public WaterTile(Point coordinates) {
		super(coordinates);
		textureName = "water";
	}

	@Override
	public Color getColor() {
		return Color.CYAN;
	}

}

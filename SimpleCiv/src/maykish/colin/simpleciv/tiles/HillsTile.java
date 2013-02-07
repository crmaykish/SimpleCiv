package maykish.colin.simpleciv.tiles;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.util.Point;

public class HillsTile extends Tile {

	public HillsTile(Point coordinates) {
		super(coordinates);
		textureName = "hills";
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

}

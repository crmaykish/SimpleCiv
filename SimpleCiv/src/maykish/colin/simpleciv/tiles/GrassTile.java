package maykish.colin.simpleciv.tiles;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.util.Point;

public class GrassTile extends Tile{

	public GrassTile(Point coordinates) {
		super(coordinates);
		textureName = "grass";
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}
	
}

package maykish.colin.simpleciv.tiles;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.util.Point;

public class DeepWaterTile extends Tile{

	public DeepWaterTile(Point coordinates) {
		super(coordinates);
		textureName = "deepwater";
	}

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

}

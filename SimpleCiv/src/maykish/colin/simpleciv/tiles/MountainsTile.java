package maykish.colin.simpleciv.tiles;

import com.badlogic.gdx.graphics.Color;

import maykish.colin.simpleciv.util.Point;

public class MountainsTile extends Tile{

	public MountainsTile(Point coordinates) {
		super(coordinates);
		textureName = "mountains";
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}

}

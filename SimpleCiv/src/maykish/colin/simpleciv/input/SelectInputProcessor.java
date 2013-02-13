package maykish.colin.simpleciv.input;

import maykish.colin.simpleciv.GamePlay;
import maykish.colin.simpleciv.map.Map;
import maykish.colin.simpleciv.util.Point;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class SelectInputProcessor implements InputProcessor {

	OrthographicCamera camera;
	GamePlay gamePlay;
	Map map;

	public SelectInputProcessor(OrthographicCamera camera, GamePlay gamePlay, Map map) {
		this.camera = camera;
		this.gamePlay = gamePlay;
		this.map = map;
	}

	private Point mapClickToTileCoords(int x, int y) {
		Point p = new Point();
		p.x = (int) ((x + camera.position.x - camera.viewportWidth / 2) / map
				.tileSize());
		p.y = (int) ((y + camera.position.y - camera.viewportHeight / 2) / map
				.tileSize());
		return p;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		Point tileCoords = mapClickToTileCoords(screenX, screenY);

		gamePlay.tileTouched(tileCoords);
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// Gdx.app.log("dragging", "dragging");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

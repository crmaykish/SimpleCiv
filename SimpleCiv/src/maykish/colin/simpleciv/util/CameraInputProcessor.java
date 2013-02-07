package maykish.colin.simpleciv.util;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class CameraInputProcessor implements InputProcessor {

	OrthographicCamera camera;

	private int startX, startY;
	private Vector2 cameraStart;

	public CameraInputProcessor(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		startX = screenX;
		startY = screenY;

		cameraStart = new Vector2(screenX, screenY).add(
				camera.position.x - camera.viewportWidth / 2, 
				camera.position.y - camera.viewportHeight / 2);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		if (startX == screenX && startY == screenY) {
			return false;
		}

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		camera.position.x = -screenX + camera.viewportWidth / 2 + cameraStart.x;
		camera.position.y = -screenY + camera.viewportHeight / 2 + cameraStart.y;

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

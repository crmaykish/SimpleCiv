package maykish.colin.simpleciv;

import maykish.colin.simpleciv.ui.MainMenu;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class SimpleCivGame implements ApplicationListener {
	
	OrthographicCamera camera;
	
	MainMenu mainMenu;
	
	Game game;
	boolean gameCreated = false;
	
	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		mainMenu = new MainMenu(camera);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (!gameCreated){
			if (mainMenu.newGameFlagged){
				game = new Game(camera);
				gameCreated = true;
			}
			if (mainMenu.quitFlagged){
				Gdx.app.exit();
			}
			mainMenu.render();
		}
		else {
			game.update();
			game.render();
		}
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

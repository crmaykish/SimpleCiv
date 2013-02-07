package maykish.colin.simpleciv;

import maykish.colin.simpleciv.map.Map;
import maykish.colin.simpleciv.util.CameraInputProcessor;
import maykish.colin.simpleciv.util.SelectInputProcessor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimpleCivGame implements ApplicationListener {

	// Constants
	final int WIDTH = 960;
	final int HEIGHT = 540;

	OrthographicCamera camera;
	SpriteBatch batch;
	
	// MVC all up in here
	Map map;			// model
	Renderer renderer;	// view
	GamePlay gamePlay;	// controller
	// it's not perfect, but it's a start
	
	@Override
	public void create() {
		camera = new OrthographicCamera();
//		camera.setToOrtho(true, WIDTH, HEIGHT);
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		map = new Map();
		gamePlay = new GamePlay(map);
		renderer = new Renderer(camera, map, gamePlay);
		
		// Setup input processors
		CameraInputProcessor cameraProcessor = new CameraInputProcessor(camera);
		SelectInputProcessor selectProcessor = new SelectInputProcessor(camera, gamePlay, map);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(cameraProcessor);
		multiplexer.addProcessor(selectProcessor);
		Gdx.input.setInputProcessor(multiplexer);
		
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		gamePlay.update();
		
		renderer.render();
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

package maykish.colin.simpleciv;

import maykish.colin.simpleciv.input.CameraInputProcessor;
import maykish.colin.simpleciv.input.SelectInputProcessor;
import maykish.colin.simpleciv.map.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game {
	// MVC all up in here
	private Map map; // model
	private Renderer renderer; // view
	private GamePlay gamePlay; // controller
	// it's not perfect, but it's a start

	public Game(OrthographicCamera camera) {
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

	public void update() {
		gamePlay.update();
	}

	public void render() {
		renderer.render();
	}
}

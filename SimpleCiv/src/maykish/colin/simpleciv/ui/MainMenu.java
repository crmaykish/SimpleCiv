package maykish.colin.simpleciv.ui;

import maykish.colin.simpleciv.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MainMenu {
	private OrthographicCamera camera;
	private SpriteBatch menuBatch;
	private TextureManager texMan;

	private Vector2 backgroundPos;
	private Vector2 logoPos;
	private Vector2 shiftSpeed;

	private Vector2 newPos;
	private Vector2 quitPos;

	public boolean quitFlagged = false;
	public boolean newGameFlagged = false;

	public MainMenu(OrthographicCamera camera) {
		this.camera = camera;

		menuBatch = new SpriteBatch();

		texMan = new TextureManager();

		backgroundPos = new Vector2(0, 0);
		logoPos = new Vector2(10, 0);
		shiftSpeed = new Vector2(-0.5f, -0.5f);

		quitPos = new Vector2(Gdx.graphics.getWidth() - 256, Gdx.graphics.getHeight() - 64);
		newPos = new Vector2(Gdx.graphics.getWidth() - 256, Gdx.graphics.getHeight() - 128);
	}

	public void render(){
		
		checkForButtonClicks();
		
		updateBackground();
		
		menuBatch.setProjectionMatrix(camera.combined);
		menuBatch.begin();
		
		menuBatch.draw(texMan.get("background"), backgroundPos.x, backgroundPos.y);
		menuBatch.draw(texMan.get("logo"), logoPos.x, logoPos.y);
		
		menuBatch.draw(texMan.get("newgame"), newPos.x, newPos.y);
		menuBatch.draw(texMan.get("quit"), quitPos.x, quitPos.y);
		
		menuBatch.end();
	}
	
	// If one of the menu buttons is clicked, set a flag that the main game loop can 
	// check on and respond to
	private void checkForButtonClicks() {
		if (Gdx.input.isTouched()){
			int screenX = Gdx.input.getX();
			int screenY = Gdx.input.getY();
			
			// TODO: these checks can be a lot cleaner
			if (screenX > Gdx.graphics.getWidth() - 256 && screenY > Gdx.graphics.getHeight() - 128 && screenY <  Gdx.graphics.getHeight() - 64){
				newGameFlagged = true;
			}
			if (screenX > Gdx.graphics.getWidth() - 256 && screenY > Gdx.graphics.getHeight() - 64){
				quitFlagged = true;
			}
		}
	}
	
	private void updateBackground(){
		if (backgroundPos.x <= -(2048 - Gdx.graphics.getWidth()) || backgroundPos.x > 0){
			shiftSpeed.x *= -1;
		}
		
		if (backgroundPos.y <= -(1024 - Gdx.graphics.getHeight()) || backgroundPos.y > 0){
			shiftSpeed.y *= -1;
		}
		
		backgroundPos.x += shiftSpeed.x;
		backgroundPos.y += shiftSpeed.y;
	}
}

package maykish.colin.simpleciv;

import maykish.colin.simpleciv.map.Map;
import maykish.colin.simpleciv.units.Unit;
import maykish.colin.simpleciv.util.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer {

	private SpriteBatch batch;
	private Map map;
	private OrthographicCamera camera;
	private GamePlay gamePlay;
	private TextureManager texMan;
	private BitmapFont font;
	
	public Renderer(OrthographicCamera camera, Map map, GamePlay gamePlay){
		this.camera = camera;
		this.map = map;
		this.gamePlay = gamePlay;
		
		texMan = new TextureManager();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setScale(1, -1);
	}
	
	public void render(){
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawMap();
		drawMapDetails();
		
//		drawFog();
		
		drawUI();
		
//		batch.draw(map.generateMinimap(), 10, 10);
		
		batch.end();
	}
	
//	private void drawFog(){
//		batch.draw(texMan.get("fog"), 100, 100);
//	}
	
	private void drawMap(){
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				
				// Is the tile visible?
				if (map.getTileAt(i, j).isVisible()){
				
					// Draw the tile texture
					batch.draw(texMan.get(map.getTileAt(i, j).getTextureName()), map.tileSize() * i, map.tileSize() * j);
					
					// Draw tile's top unit if it has one
					if (map.getTileAt(i, j).hasUnits()){
						
						// Draw the unit's texture
						Unit topUnit = map.getTileAt(i, j).getTopUnit();
						batch.draw(texMan.get(topUnit.getTextureName()), map.tileSize() * i, map.tileSize() * j);
						
						// Draw the unit's status bar
						String playerName = topUnit.owner.getName();
						String status = topUnit.getUnitStatus();
						
						font.setColor(topUnit.owner.color);
						font.drawMultiLine(batch, playerName, map.tileSize() * i + 5, map.tileSize() * j + 5);
						font.setColor(Color.WHITE);
						font.drawMultiLine(batch, status, map.tileSize() * i + 5, map.tileSize() * j + 22);
					}
				}
				else if (map.getTileAt(i, j).isDiscovered()){
					// Draw the tile texture
					batch.draw(texMan.get(map.getTileAt(i, j).getTextureName()), map.tileSize() * i, map.tileSize() * j);
					// Draw the filter to darken it
					batch.draw(texMan.get("darken"), map.tileSize() * i, map.tileSize() * j);
				}
				else {
					batch.draw(texMan.get("clouds"), map.tileSize() * i, map.tileSize() * j);
				}
			}
		}
	}
	
	private void drawMapDetails(){
		if (map.isTileSelected()){
			// Draw the selected tile graphic
			batch.draw(texMan.get("selected"), map.getSelectedCoords().x * map.tileSize(), map.getSelectedCoords().y * map.tileSize());
		
			// Draw the valid moves if unit is selected
			for (Point p : map.getValidMoves()){
				batch.draw(texMan.get("valid"), map.tileSize() * p.x, map.tileSize() * p.y);
			}
		}
	}
	
	private void drawUI(){
//		font.draw(batch, "Day: " + gamePlay.getTurnsPassed(), loc.x, loc.y);
		// TODO: don't hardcode this
		Point statsBoxLoc = convertToScreenCoords(Gdx.graphics.getWidth() - 128, 0);
		
		
		batch.draw(texMan.get("stats"), statsBoxLoc.x, statsBoxLoc.y);
		font.draw(batch, "Day: " + gamePlay.getTurnsPassed(), statsBoxLoc.x + 5, statsBoxLoc.y + 5);
		font.draw(batch, "Turn: " + gamePlay.getCurrentPlayerName(), statsBoxLoc.x + 5, statsBoxLoc.y + 25);
		font.draw(batch, "______________", statsBoxLoc.x + 6, statsBoxLoc.y + 30);
		font.draw(batch, "Players:", statsBoxLoc.x + 5, statsBoxLoc.y + 50);
		font.setColor(gamePlay.player1.color);
		font.draw(batch, gamePlay.player1.getName(), statsBoxLoc.x + 5, statsBoxLoc.y + 68);
		font.setColor(gamePlay.player2.color);
		font.draw(batch, gamePlay.player2.getName(), statsBoxLoc.x + 5, statsBoxLoc.y + 87);
		
	}
	
//	private void drawMinimap(){
//		
//	}
	
	private Point convertToScreenCoords(int x, int y){
		return new Point((int) (camera.position.x - camera.viewportWidth / 2) + x, (int) (camera.position.y - camera.viewportHeight / 2) + y);
	}
	
}

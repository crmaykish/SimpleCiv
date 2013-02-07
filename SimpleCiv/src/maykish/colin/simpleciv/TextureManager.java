package maykish.colin.simpleciv;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureManager {

	private HashMap<String, TextureRegion> textureMap;
	
	public TextureManager(){
		textureMap = new HashMap<String, TextureRegion>();
		
		loadTextures();
		flipTextures();
	}
	
	public TextureRegion get(String name){
		return textureMap.get(name);
	}
	
	private void flipTextures(){
		for (TextureRegion r : textureMap.values()){
			r.flip(false, true);
		}
	}
	
	private void loadTextures(){
		// Tiles
		textureMap.put("grass", new TextureRegion(new Texture(Gdx.files.internal("grass.png"))));
		textureMap.put("hills", new TextureRegion(new Texture(Gdx.files.internal("hills.png"))));
		textureMap.put("mountains", new TextureRegion(new Texture(Gdx.files.internal("mountain.png"))));
		textureMap.put("water", new TextureRegion(new Texture(Gdx.files.internal("water.png"))));
		textureMap.put("deepwater", new TextureRegion(new Texture(Gdx.files.internal("deepwater.png"))));
		
		textureMap.put("clouds", new TextureRegion(new Texture(Gdx.files.internal("clouds.png"))));
		textureMap.put("darken", new TextureRegion(new Texture(Gdx.files.internal("darken.png"))));
		
		// Effects
//		textureMap.put("fog", new TextureRegion(new Texture(Gdx.files.internal("fog.png"))));
		
		// UI
		textureMap.put("selected", new TextureRegion(new Texture(Gdx.files.internal("selected.png"))));
		textureMap.put("valid", new TextureRegion(new Texture(Gdx.files.internal("valid.png"))));
		textureMap.put("stats", new TextureRegion(new Texture(Gdx.files.internal("gamestatsbox.png"))));
		
		// Units
		textureMap.put("warrior", new TextureRegion(new Texture(Gdx.files.internal("warrior.png"))));
		textureMap.put("ranger", new TextureRegion(new Texture(Gdx.files.internal("archer.png"))));
		textureMap.put("scout", new TextureRegion(new Texture(Gdx.files.internal("scout.png"))));
		textureMap.put("ship", new TextureRegion(new Texture(Gdx.files.internal("boat.png"))));
		
		textureMap.put("camp", new TextureRegion(new Texture(Gdx.files.internal("castle.png"))));
		
	}
}

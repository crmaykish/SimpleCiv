package maykish.colin.simpleciv;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "SimpleCiv";
		cfg.useGL20 = true;
		cfg.width = 1600;
		cfg.height = 900;
		
		new LwjglApplication(new SimpleCivGame(), cfg);
	}
}

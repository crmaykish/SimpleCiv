package maykish.colin.simpleciv.map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

import maykish.colin.simpleciv.tiles.GrassTile;
import maykish.colin.simpleciv.tiles.Tile;
import maykish.colin.simpleciv.util.Point;

public class Map {
	private final int tileSize = 128;
	
	private Tile[][] map;
	private int mapWidth = 40;
	private int mapHeight = 30;

	private Point selectedTile;
	private boolean selected;

	public Map() {
		
		// Generate the map
		MapGenerator mapGen = new PerlinMapGenerator();
		map = mapGen.generateMap(mapWidth, mapHeight);
		
		selectedTile = new Point(-1, -1);

	}

	public int getWidth(){
		return mapWidth;
	}
	
	public int getHeight(){
		return mapHeight;
	}
	
//	public TextureRegion generateMinimap(){
//		Pixmap pixmap = new Pixmap(128, 128, Format.RGBA8888);
//		
//		for (int i = 0; i < 128; i++){
//			for (int j = 0; j < 128; j++){
//				
//				
//				
//				pixmap.setColor(map[i][j].getColor());
//				
//				pixmap.drawPixel(i, j);
//			}
//		}
//		
//		
//		
//		Texture texture = new Texture(pixmap);
//		TextureRegion region = new TextureRegion(texture);
//		region.flip(false, true);
//		return region;
//	}
	
	/**
	 * Calculate the valid moves for a unit positioned at the selected tile.
	 * @return List of Points that are valid moves based on the unit's current moves.
	 */
	public ArrayList<Point> getValidMoves(){
		return getValidMoves(selectedTile);
	}
	
	public ArrayList<Point> getValidMoves(Point start){
		ArrayList<Point> points = new ArrayList<Point>();
		
//		if (selected){
		int x = start.x;
		int y = start.y;
		
		if (getTileAt(start).hasUnits()){
			int range = getTileAt(start).getTopUnit().currentMoves;
			
			for (int i = y - range; i <= y + range; i++){
				for (int j = x - range; j <= x + range; j++){
					if (i != y || j != x){
						points.add(new Point(j, i));
					}
				}
			}
		}
//		}
		
		return points;
	}
	
	public void setAllTilesToInvisible(){
		for (int i = 0; i < mapWidth; i++){
			for (int j = 0; j < mapHeight; j++){
				map[i][j].setVisible(false);
			}
		}
	}
	
	public void setTileVisible(Point loc, boolean set){
		map[loc.x][loc.y].setVisible(set);
		setTileDiscovered(loc);
	}
	
	public void setTileDiscovered(Point p){
		map[p.x][p.y].setDiscovered(true);
	}
	
	public Tile getTileAt(Point p){
		return map[p.x][p.y];
	}
	
	public Tile getTileAt(int x, int y){
		return map[x][y];
	}
	
	public Tile getSelectedTile(){
		return map[selectedTile.x][selectedTile.y];
	}
	
	public Point getSelectedCoords(){
		return selectedTile;
	}
	
	public void setSelectedTile(Point s){
		selected = true;
		selectedTile = s;
	}
	
	/**
	 * Is there a selected tile on the map?
	 * @return true if any tile is selected, false if there are no selected tiles.
	 */
	public boolean isTileSelected(){
		return selected;
	}
	
	public void clearSelectedTile(){
		selectedTile.x = -1;
		selectedTile.y = -1;
		selected = false;
	}

	public int tileSize() {
		return tileSize;
	}
}

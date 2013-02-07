package maykish.colin.simpleciv.util;

public class Point {
	public int x;
	public int y;
	
	public Point(){
		this.x = 0;
		this.y = 0;
	}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point add(Point p){
		return new Point(this.x + p.x, this.y + p.y);
	}
	
	public Point subtract(Point p){
		return new Point(this.x - p.x, this.y - p.y);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		
		if (x == other.x && y == other.y){
			return true;
		}
		return false;
	}
	
}

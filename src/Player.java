import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{
	
	public boolean rigth, up, down, left;
	public int speed = 4;
	
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		if(rigth && World.isFree(x+speed, y)) {
			x += speed;
		}else if(left && World.isFree(x-speed, y)) {
			x -= speed;
		}
		
		if(up && World.isFree(x, y-speed)) {
			y -= speed;
		}else if(down && World.isFree(x, y + speed)) {
			y += speed;
		}
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}

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
		if(up) {
			y-=speed;
		}else if(down) {
			y+=speed;
		}
		
		if(rigth) {
			x+=speed;
		}else if(left) {
			x-=speed;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
}

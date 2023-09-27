package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

	public boolean rigth, up, down, left;
	public int speed = 4;

	public int currentAnimation = 0;
	public int currentFrames = 0, currentTarget = 15;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();

	public boolean shoot = false;
	public int dir = 1;

	public Player(int x, int y) {
		super(x, y, 32, 32);
	}

	public void tick() {
		boolean moved = false;
		if (rigth && World.isFree(x + speed, y)) {
			x += speed;
			moved = true;
			dir = 1;
		} else if (left && World.isFree(x - speed, y)) {
			x -= speed;
			moved = true;
			dir = -1;
		}

		if (up && World.isFree(x, y - speed)) {
			y -= speed;
			moved = true;
		} else if (down && World.isFree(x, y + speed)) {
			y += speed;
			moved = true;
		}
		if (moved) {
			currentFrames++;
			if (currentFrames == currentTarget) {
				currentFrames = 0;
				currentAnimation++;

				if (currentAnimation == Spritesheet.player_front.length) {
					currentAnimation = 0;
				}
			}
		}

		if(shoot){
			shoot = false;	
			bullets.add(new Bullet(x, y, dir));	
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}

	public void render(Graphics g) {
		// g.setColor(Color.blue);
		// g.fillRect(x, y, width, height);

		g.drawImage(Spritesheet.player_front[currentAnimation], x, y, 32, 32, null);
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}

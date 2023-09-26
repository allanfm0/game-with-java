package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {

	public boolean rigth, up, down, left;
	public int speed = 4;

	public int currentAnimation = 0;
	public int currentFrames = 0, currentTarget = 15;

	public Player(int x, int y) {
		super(x, y, 32, 32);
	}

	public void tick() {
		boolean moved = false;
		if (rigth && World.isFree(x + speed, y)) {
			x += speed;
			moved = true;
		} else if (left && World.isFree(x - speed, y)) {
			x -= speed;
			moved = true;
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
	}

	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, width, height);

		g.drawImage(Spritesheet.player_front[currentAnimation], x, y, 32, 32, null);
	}
}

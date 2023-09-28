package zeldaminiclone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inimigo extends Rectangle {

	public int rigth = 1, up = 0, down = 0, left = 0;
	public int speed = 2;

	public int currentAnimation = 0;
	public int currentFrames = 0, currentTarget = 15;

	public static List<Bullet> bullets = new ArrayList<Bullet>();

	public boolean shoot = false;
	public int dir = 1;

	public Inimigo(int x, int y) {
		super(x, y, 32, 32);
	}

	public void perseguirPlayer() {
		Player player = Game.player;

		if (x < player.x && World.isFree(x + speed, y)) {
			if (new Random().nextInt(100) < 50) {
				x += speed;
			}

		} else if (x > player.x && World.isFree(x - speed, y)) {
			if (new Random().nextInt(100) < 50) {
				x -= speed;
			}
		}

		if (y < player.y && World.isFree(x, y + speed)) {
			if (new Random().nextInt(100) < 50) {
				y += speed;
			}
		} else if (y > player.y && World.isFree(x, y - speed)) {
			if (new Random().nextInt(100) < 50) {
				y -= speed;
			}
		}
	}

	public void tick() {
		boolean moved = true;

		perseguirPlayer();

		if (rigth == 1 && World.isFree(x + 1, y)) {
			x++;
		}
		if (moved) {
			currentFrames++;
			if (currentFrames == currentTarget) {
				currentFrames = 0;
				currentAnimation++;

				if (currentAnimation == Spritesheet.inimigo_front.length) {
					currentAnimation = 0;
				}
			}
		}

		if (shoot) {
			shoot = false;
			bullets.add(new Bullet(x, y, dir));
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
	}

	public void render(Graphics g) {
		// g.setColor(Color.blue);
		// g.fillRect(x, y, width, height);

		g.drawImage(Spritesheet.inimigo_front[currentAnimation], x, y, 32, 32, null);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
	}
}

package entities;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.Handler;
import states.PlayState;

public class Enemy {
	// Internal class, mostly to show how it works in case you need it.
	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	public int velX, velY;
	protected short interval = 0;
	protected PlayState playstate;
	private int lives = 5;
	private int pwrup;

	public Enemy(int x, int y, int width, int height, Id id, PlayState playstate, int pwrup) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.playstate = playstate;
		this.pwrup = pwrup;

	}

	public void draw(GraphicsContext g) {

	g.drawImage(image, x, y, width, height);	
	}

	public void setVelX(int velX) {

		this.velX = velX;

	}

	public void update() {
		
		x+=velX;
		y+=velY;
		checkCoordinates();
		interval++;
		fireVirus(playstate);
		CollisionCheck();


	}
	
		
		public void checkCoordinates() {

			if (this.getX() <= 0) {
				velX = -1 * velX;

			}

			if (this.getX() >= Constants.SCREEN_WIDTH - this.getWidth() - 210) {
				velX = -1 * velX;
			}
		
	}
	

	protected void CollisionCheck() {

		for (int i = 0; i < playstate.bullets.size(); i++) {

			Weapon bullet = playstate.bullets.get(i);

			if (bullet.getId() == Id.friendlyFire) {
				
				if (this.getBounds().intersects(bullet.getBounds())) {
					playstate.deleteWeapon(bullet);
					this.damaged(bullet);
					bullet.explode(playstate, velX, velY);
					// playstate.on(playstate.bullets.get(i));deleteWeap
				}
			}

		}

	}

	private void damaged(Weapon bullet) {
		lives += bullet.getDamage();
		if (lives <= 0) {
			playstate.getPlayer().addScore(10);
			this.die();
		} else {
			playstate.getPlayer().addScore(2);
		}

	}

	
	public void fireVirus(PlayState playstate) {

	}

	

	public void die() {
		playstate.deleteEnemy(this);
		

		Explosion e = new Explosion(this.x, this.y, this.width, this.height, velX, velY, playstate);
		playstate.addExplosion(e);
		
		if(pwrup == 1) {
			
			playstate.addBullet(new PowerUp(getX() + getWidth() / 2, this.getY(),20,20,5, Id.bulletpwrup1));	
			
			}
		
		if(pwrup == 2) {
		playstate.addBullet(new PowerUp(getX() + getWidth() / 2, this.getY(),20,20,5, Id.bulletpwrup2));	
		}

	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setVelY(int velY) {
		this.velY = velY;

	}

	public void setImage(Image image) {
	
	this.image = image;
		
	}

}

package entities;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import states.Handler;
import states.PlayState;

public class Player {
	// Internal class, mostly to show how it works in case you need it.
	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private Image player;
	private int x;
	private int y;
	private int width;
	private int height;
	public int velX, velY;
	private short interval = 0;
	private PlayState playstate;
	private int lives;
	private int shootingspeed = 20;
	private int score = 0;
	private String userName;
	private int weaponNr;

	public Player(int x, int y, int width, int height, Id id, int lives, PlayState playstate, String userName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playstate = playstate;
		this.lives = lives;
		this.userName = userName;

		weaponNr = 0;
		try {
			player = new Image(new FileInputStream("Player.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

	}

	public void setNewPlayState(PlayState playstate) {
		this.playstate = playstate;
	}

	public void addScore(int scorevariable) {
		score += scorevariable;

	}

	public void draw(GraphicsContext g) {

		g.drawImage(player, x, y, width, height);

	}

	public void setVelX(int velX) {

		this.velX = velX;

	}

	public void update() {

		this.x += velX;
		checkCoordinates();
		this.interval++;
		CollisionCheck();

		if (lives <= 0) {
			die();

		}

	}

	private void CollisionCheck() {

		for (int i = 0; i < playstate.bullets.size(); i++) {

			Weapon weapon = playstate.bullets.get(i);

			if (this.getBounds().intersects(weapon.getBounds())) {

				if (weapon.getId() == Id.enemyFire) {

					lives += -10;
					weapon.explode(playstate, 0, 0);
					playstate.deleteWeapon(weapon);

				}
				if (weapon.getId() == Id.bulletpwrup1) {

					playstate.deleteWeapon(weapon);
					
					weaponNr = 1;
					setShootingSpeed(7);

				}

				if (weapon.getId() == Id.bulletpwrup2) {

					setShootingSpeed(15);
					playstate.deleteWeapon(weapon);
					weaponNr = 2;

				}

				if (weapon.getId() == Id.coin) {

					playstate.deleteWeapon(weapon);
				    playstate.addNotification(new Notification(this.x,this.y, playstate, "Score + 5"));
					score+=5;

				}

			}

		}

		for (

				int i = 0; i < playstate.enemies.size(); i++) {
			Enemy enemy = playstate.enemies.get(i);
			if (enemy.getBounds().intersects(getBounds())) {
				lives += -20;
				enemy.die();
			}
		}

	}

	public void die() {

		playstate.gameOver();

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);

	}

	public void checkCoordinates() {
		if (this.x <= 0) {
			this.x = 0;
		}

		if (this.x >= Constants.SCREEN_WIDTH - this.width - 210) {
			this.x = Constants.SCREEN_WIDTH - this.width - 210;
		}
	}

	public void fireBullet() {
		if (this.interval < shootingspeed) {
			return;
		} else {
			this.interval = 0;

			if (this.weaponNr<2) {
				playstate.addBullet(new Vaxxbullet(this.x + this.width / 2, this.y - 50, 5, 60, -10));

			}
			if (this.weaponNr == 2) {
				playstate.addBullet(new BoosterVaxx(this.x + this.width / 2, this.y - 50, 5, 60, -10));
			}

		}

	}

	public int getLives() {

		return lives;

	}

	public void setShootingSpeed(int speed) {

		this.shootingspeed = speed;
	}

	public int getScore() {

		return this.score;
	}

	public String getUserName() {

		return this.userName;
	}

	public Image getWeapon() {

		if (weaponNr < 2) {
			return Vaxxbullet.getImage();
		}
		if (weaponNr == 2) {
			return BoosterVaxx.getImage();
		}

		return null;

	}

	public int getWeaponNr() {
		
		return this.weaponNr;
	}

}

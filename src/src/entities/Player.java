package entities;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Player {

	private Image player, player1, player2, player1left, player1right, player2left, player2right;
	private int x;
	private int y;
	private int width;
	private int height;
	public int velX, velY;
	private short interval = 0;
	private PlayState playstate;
	private int lives;
	private int shootingspeed = 15;
	private int score = 0;
	private String userName;
	private int weaponNr;
	private Id id;

	public Player(int x, int y, int width, int height, Id id, int lives, PlayState playstate, String userName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playstate = playstate;
		this.lives = lives;
		this.userName = userName;
		this.id = id;

		weaponNr = 0;
		try {
			player1 = new Image(new FileInputStream("gus1.png"));
			player1left = new Image(new FileInputStream("gusLeft.png"));
			player1right = new Image(new FileInputStream("gusRight.png"));

			player2 = new Image(new FileInputStream("nicho1.png"));
			player2left = new Image(new FileInputStream("nichoLeft.png"));
			player2right = new Image(new FileInputStream("nichoRight.png"));

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

		if (this.id == Id.player1) {

			if (velX == 0) {
				g.drawImage(player1, x, y, width, height);
			}
			if (velX < 0) {
				g.drawImage(player1left, x, y + 15, height, width);
			}
			if (velX > 0) {
				g.drawImage(player1right, x, y + 15, height, width);
			}

		} else if (this.id == Id.player2) {

			if (velX == 0) {
				g.drawImage(player2, x, y, width, height);
			}
			if (velX < 0) {
				g.drawImage(player2left, x, y + 15, height, width);
			}
			if (velX > 0) {
				g.drawImage(player2right, x, y + 15, height, width);

			}
		}

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
					playstate.addNotification(new Notification(this.x, this.y, 20, playstate, "Lives - 10"));

				}
				if (weapon.getId() == Id.bulletpwrup1) {

					playstate.deleteWeapon(weapon);

					weaponNr = 1;
					setShootingSpeed(7);
					playstate.addNotification(new Notification(this.x, this.y, 20, playstate, "Reload speed x2"));

				}

				if (weapon.getId() == Id.bulletpwrup2) {

					setShootingSpeed(15);
					playstate.deleteWeapon(weapon);
					weaponNr = 2;
					playstate.addNotification(new Notification(this.x, this.y, 20, playstate, "New weapon"));

				}

				if (weapon.getId() == Id.coin) {

					playstate.deleteWeapon(weapon);
					playstate.addNotification(new Notification(this.x, this.y, 20, playstate, "Score + 5"));
					score += 5;

				}

			}

		}

		for (

				int i = 0; i < playstate.enemies.size(); i++) {
			Enemy enemy = playstate.enemies.get(i);
			if (enemy.getBounds().intersects(getBounds())) {
				lives += -20;
				playstate.addNotification(new Notification(this.x, this.y, 20, playstate, "Lives - 20"));
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
			velX = 0;
			this.x = 0;
		}

		if (this.x >= Constants.SCREEN_WIDTH - this.width - 210) {
			velX = 0;
			this.x = Constants.SCREEN_WIDTH - this.width - 210;
		}
	}

	public void fireBullet() {
		if (this.interval < shootingspeed) {
			return;
		} else {
			this.interval = 0;

			if (this.weaponNr < 2) {
				if (velX > 0) {
					playstate.addBullet(new Vaxxbullet(this.x + this.width / 2 + 25, this.y - 50, 5, 60, -20));
				} else
					playstate.addBullet(new Vaxxbullet(this.x + this.width / 2, this.y - 50, 5, 60, -20));

			}
			if (this.weaponNr == 2) {
				if (velX > 0) {
					playstate.addBullet(new BoosterVaxx(this.x + this.width / 2 + 25, this.y - 50, 5, 60, -20));
				} else
					playstate.addBullet(new BoosterVaxx(this.x + this.width / 2, this.y - 50, 5, 60, -20));

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

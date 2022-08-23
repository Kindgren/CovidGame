package entities;

import java.awt.Rectangle;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;


/* Superclass for different enemies.  
 */

public class Enemy {

	private Image image;
	protected int x, y;
	protected int width, height;
	public int velX, velY;
	protected short interval = 0;
	protected PlayState playstate;
	private int lives = 5;
	private int pwrup;

	public Enemy(int x, int y, int width, int height, Id id, PlayState playstate, int pwrup) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.playstate = playstate;
		this.pwrup = pwrup;

	}
	
//ritar ut fienden
	public void draw(GraphicsContext g) {

		g.drawImage(image, x, y, width, height);
	}

	//sätter rörelsen i x-led
	public void setVelX(int velX) {

		this.velX = velX;

	}

	//uppdatera rörelser osv.
	public void update() {

		x += velX;
		y += velY;
		checkCoordinates();
		interval++;
		fireVirus(playstate);
		CollisionCheck();

	}
	
//kolla så fienden inte går utanför skärmen
	public void checkCoordinates() {

		if (x <= 0 || x >= Constants.SCREEN_WIDTH - width - 210) {
			velX = -1 * velX;

		}

	}

	//kollar ifall fienden blir skjuten
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
	
	
//funktion för att hålla koll på när fienden ska dö
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

	//funktion som kallas när fienden dör och det skapas en explosion
	public void die() {

		Explosion e = new Explosion(this.x, this.y, this.width, this.height, velX, velY, playstate);
		playstate.addExplosion(e);

		if (pwrup == 1) {

			playstate.addBullet(new PowerUp(x + width / 2, y, 20, 20, 5, Id.bulletpwrup1));

		}

		if (pwrup == 2) {
			playstate.addBullet(new PowerUp(x + width / 2, y, 20, 20, 5, Id.bulletpwrup2));
		}

		playstate.deleteEnemy(this);

	}

	//returnerar en rektangel på fiendens position som används vid kollisioner
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}




	public void setVelY(int velY) {
		this.velY = velY;

	}

	public void setImage(Image image) {

		this.image = image;

	}

}

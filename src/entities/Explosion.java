package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Explosion {

	private Image[] sprite = new Image[7];
	private int x, y, velX, velY, width, height;
	private short interval;
	private int i;
	private PlayState playstate;

	public Explosion(int x, int y, int width, int height,int velX, int velY, PlayState playstate) {

		this.playstate = playstate;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		

		interval = 0;
		i = 0;

		try {
			sprite[0] = new Image(new FileInputStream("explodesprite1.png"));
			sprite[1] = new Image(new FileInputStream("explodesprite2.png"));
			sprite[2] = new Image(new FileInputStream("explodesprite3.png"));
			sprite[3] = new Image(new FileInputStream("explodesprite4.png"));
			sprite[4] = new Image(new FileInputStream("explodesprite5.png"));
			sprite[5] = new Image(new FileInputStream("explodesprite6.png"));
			sprite[6] = new Image(new FileInputStream("explodesprite7.png"));
			
			
			
			

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public void draw(GraphicsContext g) {

		if (this.interval > 1) {
			this.interval = 0;
			i++;
		}

		if (i > 4) {
			playstate.deleteExplosion(this);
			return;
		} else {
			g.drawImage(sprite[i], x, y, width, height);
		}

	}

	public void update() {

		this.x+=velX;
		this.y+=velY;
		interval++;

	}

}

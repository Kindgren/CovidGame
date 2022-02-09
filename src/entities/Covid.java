package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Covid extends Enemy{

	private Image enemy;
	public Covid(int x, int y, int width, int height, Id id, PlayState playstate, int i) {
		super(x, y, width, height, id, playstate, i);
		
		velX = 3;
		
		super.setVelX(velX);
		
		try {
			enemy = new Image(new FileInputStream("covidred.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
	}
	
	public void draw(GraphicsContext g) {

		g.drawImage(enemy, getX(), getY(), getWidth(), getHeight());

	}
	


	
	
	public void fireVirus(PlayState playstate) {
		if (this.interval < playstate.randomIntGenerator(7000)) {
			return;
		} else {
			this.interval = 0;

			Weapon bullet = new CovidBullet(this.getX() + this.getWidth() / 2, this.getY(), 10,20,10);
			playstate.addBullet(bullet);

		}
		

	}
	
	

	}
	
	
	
	
	
	



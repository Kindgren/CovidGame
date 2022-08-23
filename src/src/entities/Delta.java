package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Delta extends Enemy {
	private Image enemy;

	public Delta(int x, int y, int width, int height, Id id, PlayState playstate, int pwrup, int leftOrRight) {
		super(x, y, width, height, id, playstate, pwrup);
		
		velX=7*leftOrRight;
		velY=7*leftOrRight;
		
		super.setVelX(velX);
		super.setVelY(velY);
		
		

		try {
			enemy = new Image(new FileInputStream("covidgreen.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
		
		super.setImage(enemy);
		
	}
	
	
	
	private int randomNegIntGenerator() {

		Random dice = new Random();
		int number;
		number = dice.nextInt(7-5)+5;
		return number;
	}
	
	
public void checkCoordinates() {
		
	
		
		if (x <= 0) {
			super.setVelX(randomNegIntGenerator()); 
		//	setVelY(randomIntGenerator());
			
		}

		if (x >= Constants.SCREEN_WIDTH - width - 210) {
			super.setVelX(-1*randomNegIntGenerator());
		
		//	setVelY(randomIntGenerator());
		}
		
		if (y <= 0) {
			super.setVelY(randomNegIntGenerator());
		//	setVelX(randomIntGenerator());
			
		}

		if (y >= Constants.SCREEN_HEIGHT - width - 40) {
			super.setVelY(-1*randomNegIntGenerator());
		//	setVelX(randomIntGenerator());
		}
		
		
		
	}

	
	


}

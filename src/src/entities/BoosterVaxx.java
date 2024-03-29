package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

/* Weapon class for the weapon the player gets when collecting the powerup wich contains it.
 * Has more damage than the original weapon. 

*/
public class BoosterVaxx extends Weapon {

			private static Image bullet;
		

			public BoosterVaxx(int x, int y, int width, int height, int velY) {
				super(x, y, width, height, velY);
				
				super.setId(Id.friendlyFire);
				super.setImage(bullet);
				
				setDamage(-5);

			}
			
			
			public static Image getImage() {

				try {
					bullet = new Image(new FileInputStream("superbullet.png"));

				} catch (FileNotFoundException e) {
					System.out.println("Unable to find image-files!");
				}

				return bullet;
			}
}

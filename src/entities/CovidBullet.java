package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class CovidBullet extends Weapon {

	private Image bullet;

	public CovidBullet(int x, int y, int width, int height, int velY) {
		super(x, y, width, height, velY);

		super.setId(Id.enemyFire);

		try {
			bullet = new Image(new FileInputStream("Covidbullet.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		super.setImage(bullet);

	}

}

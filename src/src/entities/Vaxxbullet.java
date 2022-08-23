package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Vaxxbullet extends Weapon {

	private static Image bullet;

	public Vaxxbullet(int x, int y, int width, int height, int velY) {
		super(x, y, width, height, velY);

		super.setId(Id.friendlyFire);
		super.setImage(bullet);

		setDamage(-1);

	}


	public static Image getImage() {

		try {
			bullet = new Image(new FileInputStream("playerbullet.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		return bullet;
	}

}

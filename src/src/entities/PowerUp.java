package entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PowerUp extends Weapon {

    

	private Image pwrup1, pwrup2,coin;
	
	public PowerUp(int x, int y, int width, int height,int velY, Id type) {
		super(x,y,width,height,velY);
		
		super.setId(type);
		
		try {
			pwrup1 = new Image(new FileInputStream("coin1.png"));
			pwrup2 = new Image(new FileInputStream("coin2.png"));
			coin = new Image(new FileInputStream("coin.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	
	}
	
	public void draw(GraphicsContext g) {
		
        if (getId()==Id.bulletpwrup1) {
        	g.drawImage(pwrup1, super.x, super.y, super.width, super.height);	
        }
        
        if (getId()==Id.bulletpwrup2) {
        	g.drawImage(pwrup2, super.x, super.y, super.width, super.height);	
        }
        if (getId()==Id.coin) {
        	g.drawImage(coin, super.x, super.y, super.width, super.height);	
        }
		
		

	}

}

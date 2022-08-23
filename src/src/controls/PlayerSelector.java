package controls;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class PlayerSelector {

	private int x, y;
	private int width, height;
	private String label;
	private boolean marked;
	private Image p1,p2;


	public PlayerSelector(int x, int y, int width, int height, String label, boolean marked) {

		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.label = label;
		this.marked=marked;
		
		
		try {
			 p1 = new Image(new FileInputStream("gus.png"));
	         p2 =  new Image(new FileInputStream("nicole.png")); 
				
			} catch (FileNotFoundException e) {
				System.out.println("Unable to find image-files!");
			}
		
	
		

	}

	public void draw(GraphicsContext g) {
		// g.setFill(Color.BEIGE);
		// g.fillRect(x, y, width, height);

		if (this.label=="p1") {
		g.drawImage(p1, x,y, 30,60);
		}else
		g.drawImage(p2, x,y, 30,60);
		if (marked) {

			g.setStroke(Color.WHITE);
			g.strokeRect(x, y, width, height);

		}

	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public double getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public String getLabel() {
		return this.label;
	}

	public void setAsMarked() {
	marked=true;
		
	}

	public void setAsUnmarked() {
	marked=false;
		
	}
	
	public Boolean activated() {
	return marked;	
	}
	

}
package controls;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MenuButton  {

	private int x, y;
	private int width, height;
	private String label;
	private boolean marked = false;

	public MenuButton(int x, int y, int width, int height, String label) {

		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.label = label;

	}

	public void draw(GraphicsContext g) {
		// g.setFill(Color.BEIGE);
		// g.fillRect(x, y, width, height);
		
		

		

		g.setFill(Color.WHITE);
		g.setFont(new Font("cooper black", 30));
		g.fillText(label, x, y+height-10);

		g.setStroke(Color.BLACK);
		g.strokeText(label, x, y+height-10);
	

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

}
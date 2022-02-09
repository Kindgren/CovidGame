package states;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Constants;
import controls.MenuButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverState extends GameState {

	private Image image;
	private int score;
	private MenuButton viewHighscores;
	public GameOverState(GameModel model, int score) {
		super(model);
		this.score=score;
		
		try {
			image = new Image(new FileInputStream("Gameover.png"));

		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	
	
	viewHighscores = new MenuButton(710, 100, 150, 50, "Highscores");
	
	}

	@Override
	public void update() {
		

	}

	@Override
	public void draw(GraphicsContext g) {
	drawBg(g, Color.LIGHTBLUE);	
	g.drawImage(image, 0, 0,720, 720);
	g.setFill(Color.BLACK);
	g.setFont(new Font(60));
	g.fillText("GAME OVER", 200, 100);
	g.fillText(null, score, score, score);
	g.setFont(new Font(30));
	g.fillText("Score: "+ score, 300, 170);
	
	

	g.strokeLine(700, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);

	g.setFill(Color.RED);
	g.setFont(new Font(30));
	
	viewHighscores.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		int mx = (int) event.getX();
		int my = (int) event.getY();

		if (mx >= viewHighscores.getX() && mx <= viewHighscores.getX() + viewHighscores.getWidth()) {
			if (my >= viewHighscores.getY() && my <= viewHighscores.getY() + viewHighscores.getHeight()) {

				if (viewHighscores.getLabel() == "Highscores") {
					model.switchState(new HighScoreState(model));
				}

			}
		}
	}

	

	@Override
	protected void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}

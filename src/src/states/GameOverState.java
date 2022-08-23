package states;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import constants.Constants;
import controls.MenuButton;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverState extends GameState {

	private Image image;
	private Player player;
	private MenuButton viewHighscores;
	public GameOverState(GameModel model, Player player) {
		super(model);
		this.player=player;
		
		try {
			image = new Image(new FileInputStream("tombstone1.png"));

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
	drawBgImage(g);	
	g.drawImage(image, 260, 250,255,360);
	g.setFill(Color.WHITE);
	
	g.setFont(new Font("cooper black", 60));
	g.fillText("GAME OVER", 200, 100);
	g.strokeText("GAME OVER", 200, 100);
	//g.fillText(null, score, score, score);
	g.setFont(new Font("cooper black", 30));
	g.fillText("Score: "+ player.getScore(), 320, 150);
	g.strokeText("Score: "+ player.getScore(), 320, 150);
	
	g.setFont(new Font("arial", 25));
	g.setFill(Color.DARKGRAY.darker().darker().darker());
	g.setStroke(Color.BLACK);
	
	g.strokeText(player.getUserName(), 370-(player.getUserName().length()*7), 500);

	g.fillText(player.getUserName(), 370-(player.getUserName().length()*7), 500);


	
	

	g.strokeLine(700, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);

	

	
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
					model.switchState(new HighScoreState(model,player));
				}

			}
		}
	}

	

	@Override
	protected void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}

package states;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import constants.Constants;
import controls.MenuButton;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HighScoreState extends GameState {

	private int[] highscores = new int[11];
	private String[] names = new String[11];
	private MenuButton mainMenu;
	private Player player;

	public HighScoreState(GameModel gameModel, Player player) {
		super(gameModel);
		this.player = player;
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Highscores.txt"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		int i = 0;

		String tmp = scanner.nextLine();

		names = tmp.split(" ");

		while (scanner.hasNextInt()) {
			highscores[i++] = scanner.nextInt();
		}

		mainMenu = new MenuButton(710, 100, 150, 50, "Main Menu");

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GraphicsContext g) {

		drawBgImage(g);

		g.setFill(Color.LIGHTBLUE);
		g.fillRect(190, 90, 270, 510);
		g.setStroke(Color.BLACK);
		g.strokeRect(190, 90, 270, 510);

		g.setFont(new Font("cooper black", 50));
		g.setFill(Color.WHITE);
		g.fillText("Highscores: ", 200, 60);
		g.strokeText("Highscores: ", 200, 60);

		g.setStroke(Color.WHITE);
		g.strokeLine(700, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);

		g.setFont(new Font("cooper black", 30));

		for (int i = 0; i < 10; i++) {

			if (i < 9) {
				g.fillText(names[i], 230, 120 + i * 50);

				g.fillText(i + 1 + ".                     " + String.valueOf(highscores[i]), 200, 120 + i * 50);

				if (player != null) {
					if (highscores[i] == player.getScore() && names[i].contains(player.getUserName())) {
						g.strokeOval(380, 90 + i * 50, 70, 50);
					}
				}

			} else {
				g.fillText(names[i], 240, 120 + i * 50);

				g.fillText(i + 1 + ".                   " + String.valueOf(highscores[i]), 200, 120 + i * 50);

				if (player != null) {
					if (highscores[i] == player.getScore() && names[i].contentEquals(player.getUserName())) {
						g.strokeOval(380, 90 + i * 50, 70, 50);
					}
			}

		}
		}

		mainMenu.draw(g);

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

		if (mx >= mainMenu.getX() && mx <= mainMenu.getX() + mainMenu.getWidth()) {
			if (my >= mainMenu.getY() && my <= mainMenu.getY() + mainMenu.getHeight()) {

				if (mainMenu.getLabel() == "Main Menu") {
					model.switchState(new MenuState(model));
				}

			}
		}
	}

	@Override
	protected void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}

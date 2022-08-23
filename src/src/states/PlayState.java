package states;

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import constants.Constants;
import entities.Enemy;
import entities.Explosion;
import entities.Id;
import entities.Notification;
import entities.Player;
import entities.PowerUp;
import entities.Vaxxbullet;
import entities.Weapon;

/**
 * This state represents the Playing State of the Game The main responsibility
 * of this class is to; - create Game Objects - update Game Objects - draw Game
 * Objects Game Objects are for instance; players, enemies, npc's, etc...
 *
 * The PlayState can also be thought off as a blue print where data is loaded
 * into some container from a file or some other type of data storage.
 *
 * It can also be created by some class responsible for object creation and then
 * passed to the PlayState as a parameter. This means all the PlayState has to
 * do is receive a list of objects, store them in some container and then for
 * every object in that container update and render that object.
 *
 * This way you can let the user define different Levels based on what
 * parameters are passed into the PlayState.
 */
public class PlayState extends GameState {

	private Player player;

	// listor för att lagra olika fiender, vapen och explosioner
	public ArrayList<Weapon> bullets = new ArrayList<Weapon>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Explosion> explosions = new ArrayList<Explosion>();

	// den banan som är aktuell för tillfället
	public Levels currentlevel;

	// notifikation som visar tydlig visar vad som händer när när du tar ett mynt
	// t.ex.
	public Notification notification = null;

	// siffra som används för att visa vilken bana som ska skapas
	private int lvl;

	// interval för när coins ska släppas
	private short interval = 0;
	// boolean för att se om spelet är pausat eller inte
	private boolean pause;

	public PlayState(GameModel model, int lvl) {
		super(model);

		this.lvl = lvl;
		pause = false;

	}

	// skapar en ny spelare, görs bara när man startar ett nytt spel
	public void initiatePlayer(String userName, Id id) {

		player = new Player(10, 580, 40, 80, id, 100, this, userName);
	}

	// skickar vidare spelaren så att den följer med till nästa bana
	public void setPlayer(Player player) {

		this.player = player;

	}

	// Ritar ut spelet och delegerar till entiteterna som finns att rita ut sig
	// själva
	public void draw(GraphicsContext g) {

		if (pause == false) {

			drawBg(g, currentlevel.getBgColor());

			player.draw(g);

			for (int i = 0; i < bullets.size(); i++) {

				bullets.get(i).draw(g);

			}

			for (int i = 0; i < enemies.size(); i++) {

				enemies.get(i).draw(g);
				

			}

			for (int i = 0; i < explosions.size(); i++) {

				explosions.get(i).draw(g);
			}

			g.setFill(Color.BLACK);
			g.setFont(new Font("Corier", 20));
			g.fillText("Score: " + player.getScore(), 720, 50);
			g.fillText("Lives: " + player.getLives(), 720, 90);

			g.setStroke(Color.BLACK);
			g.strokeLine(700, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);

			g.fillText("Weapon : ", 720, 130);
			g.drawImage(player.getWeapon(), 830, 100, 10, 40);

			g.fillText("Reload speed: ", 720, 170);
			if (player.getWeaponNr() == 1) {
				g.fillText("x2", 850, 170);
			} else {
				g.fillText("x1", 850, 170);
			}

			if (notification != null) {
				notification.Draw(g);

			}

		} else {
			g.fillText("PAUSED", 300, 300);
		}

	}

	// uppdaterar rörelser för entiter och sånt som händer i spelet
	public void update() {

		if (pause == false) {

			interval++;

			releaseCoins();
			player.update();

			for (int i = 0; i < enemies.size(); i++) {

				enemies.get(i).update();
				;
			}

			for (int i = 0; i < bullets.size(); i++) {

				bullets.get(i).update();
				if(bullets.get(i).getY()>Constants.SCREEN_HEIGHT) {
			//	bullets.remove(bullets.get(i));	
				}
			}

			for (int i = 0; i < explosions.size(); i++) {

				
				explosions.get(i).update();
			}

		}

	}

	// släpper ut mynt som man kan fånga
	private void releaseCoins() {

		if (this.interval < randomIntGenerator(10000)) {
			return;
		} else {
			this.interval = 0;

			bullets.add(new PowerUp(randomIntGenerator(685), 0, 15, 15, 5, Id.coin));

		}
	}

	// randomiserare som används för att få ett random nummer
	public int randomIntGenerator(int range) {

		Random dice = new Random();
		int number;
		number = dice.nextInt(range);
		return number;
	}

	// lägger till bullets i listan
	public void addBullet(Weapon weapon) {

		this.bullets.add(weapon);

	}

	// lägger till fiender i listan
	public void addEnemy(Enemy enemy) {

		this.enemies.add(enemy);

	}

	// tar bort fiender ur listan
	public void deleteEnemy(Enemy enemy) {
		enemies.remove(enemy);

	}

	// lägger till explosioner i listan
	public void addExplosion(Explosion e) {
		explosions.add(e);

	}

	// tar bort explosioner ur listan
	public void deleteExplosion(Explosion e) {

		explosions.remove(e);
		completedCheck();

	}

	// kollar så att det fortfarande finns fiender kvar, annars byter vi till nästa
	// bana eller så är spelet färdigt
	private void completedCheck() {

		if (enemies.isEmpty() && explosions.isEmpty()) {

			lvl++;
			if (lvl == 3) {
				// score = score + lives;
				saveHighScore();

				player.addScore(player.getLives());
				model.switchState(new HighScoreState(model, player));

			} else {

				PlayState playstate = new PlayState(model, lvl);
				playstate.setPlayer(player);
				player.setNewPlayState(playstate);
				model.switchState(playstate);

			}

		}

	}

	// tar bort vapen ur listan
	public void deleteWeapon(Weapon weapon) {
		bullets.remove(weapon);

	}

	// kollar vilka tangenter som trycks på
	@Override
	public void keyPressed(KeyEvent key) {

		String s = key.getCode().toString();

		switch (s) {
		case "RIGHT":
			player.setVelX(10);
			break;
		case "LEFT":
			player.setVelX(-10);
			break;
		case "ESCAPE":
			if (pause == false) {
				pause = true;
			} else {
				pause = false;
			}

			break;
		case "SPACE":

			player.fireBullet();
		}
	}

	// aktiverar banan som vi ska vara på
	@Override
	public void activate() {
		if (lvl == 0) {
			currentlevel = new LevelOne(this);

		}
		if (lvl == 1) {
			currentlevel = new LevelTwo(this);

		}

		if (lvl == 2) {
			currentlevel = new LevelThree(this);

		}

	}

	/**
	 * We currently don't have anything to deactivate in the PlayState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void deactivate() {

	}

	// används ej här
	public void mousePressed(MouseEvent key) {

	}

	// här kollar vi när vi släpper tangenter för att spelaren ska sluta röra sig då
	// t.ex.
	@Override
	protected void keyReleased(KeyEvent event) {
		String s = event.getCode().toString();

		switch (s) {
		case "RIGHT":
			player.setVelX(0);
			break;
		case "LEFT":
			player.setVelX(0);
			break;
		}

	}

	// metod som blir kallad på när spelaren dör
	public void gameOver() {

		saveHighScore();
		model.switchState(new GameOverState(model, player));

	}

// metod för att spara highscores till fil och sortera in det nya resultatet i rätt ordning
	public void saveHighScore() {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("Highscores.txt"));
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		int[] scores = new int[11];
		String[] names = new String[10];
		String s = scanner.nextLine();

		names = s.split(" ");

		String[] names2 = new String[11];

		int i = 0;
		boolean inserted = false;
		int totalScore = player.getScore() + player.getLives();

		for (int p = 0; p < 10; p++) {

			int tmp = scanner.nextInt();

			if (tmp < totalScore && inserted == false) {

				scores[i] = totalScore;
				names2[i] = player.getUserName();
				inserted = true;

				scores[i + 1] = tmp;
				names2[i + 1] = names[i];
				i += 2;

			} else {
				scores[i] = tmp;
				names2[i] = names[p];
				i++;
			}

		}

		for (int j = 0; j < 10; j++) {

		}

		overwrite(scores, names2);

	}

	// hjälpmetod för saveHighscore metoden där vi skriver till den externa filen
	private void overwrite(int[] scores, String[] names) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Highscores.txt"))) {

			for (int i = 0; i < 10; i++) {

				writer.write(names[i] + " ");
			}

			writer.write("\n");

			for (int i = 0; i < 10; i++) {

				writer.write(String.valueOf(scores[i]) + " ");
			}

		} catch (IOException e) {

		}

	}

	// returnerar spelaren i playstate
	public Player getPlayer() {

		return this.player;
	}

	// gör en ny notifikation
	public void addNotification(Notification notification) {

		this.notification = notification;

	}

	// tar bort notifikationen
	public void deleteNotification(Notification notification) {

		notification = null;
	}

}

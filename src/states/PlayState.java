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
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the MenuState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */

	private Player player;

	/* Class only used for testing */

	public ArrayList<Weapon> bullets = new ArrayList<Weapon>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	public Levels currentlevel;
	public Notification notification = null;
	private int lvl;
	private short interval = 0;

	// vi flyttar över det som är i handler till playstate. och handler får ha
	// ansvar över vilken bana man är på istället

	public PlayState(GameModel model, int lvl) {
		super(model);

		// player = new Player(10, 580, 30, 60, Id.player, lives, this);

		this.lvl = lvl;

	}

	public void initiatePlayer(String userName) {

		player = new Player(10, 580, 30, 60, Id.player, 100, this, userName);
	}

	public void setPlayer(Player player) {

		this.player = player;
	}

	public void draw(GraphicsContext g) {
		
	
		
		
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
		g.setFont(new Font("Corier", 20)); // Big letters
		g.fillText("Score: " + player.getScore(), 720, 50);
		g.fillText("Lives: " + player.getLives(), 720, 90);

		g.strokeLine(700, 0, Constants.SCREEN_HEIGHT, Constants.SCREEN_WIDTH);

		g.fillText("Weapon : ", 720, 130);
		g.drawImage(player.getWeapon(), 830, 100, 10, 40);
		
		
		g.fillText("Reload speed: ", 720,170);
		if (player.getWeaponNr()==1) {
			g.fillText("x2", 850, 170 );	
		}else {
			g.fillText("x1", 850, 170 );	
		}
		
		
		if (notification != null) {
			notification.Draw(g);	
			}

	

	}

	public void update() {

		interval ++;
		
		releaseCoins();
		player.update();

		completedCheck();

		for (int i = 0; i < enemies.size(); i++) {

			enemies.get(i).update();
			;
		}

		for (int i = 0; i < bullets.size(); i++) {

			bullets.get(i).update();
		}

		for (int i = 0; i < explosions.size(); i++) {

			explosions.get(i).update();
		}

	}

	private void releaseCoins() {
	
		if (this.interval < randomIntGenerator(10000)) {
			return;
		} else {
			this.interval = 0;
			
			bullets.add(new PowerUp(randomIntGenerator(685), 0, 15,15, 5, Id.coin));
			
		}
	}
	
	public int randomIntGenerator(int range) {

		Random dice = new Random();
		int number;
		number = dice.nextInt(range);
		return number;
	}

	public void addBullet(Weapon weapon) {

		this.bullets.add(weapon);

	}

	public void addEnemy(Enemy enemy) {

		this.enemies.add(enemy);

	}
	

	public void deleteEnemy(Enemy enemy) {
		enemies.remove(enemy);

	}

	public void addExplosion(Explosion e) {
		explosions.add(e);

	}

	public void deleteExplosion(Explosion e) {

		explosions.remove(e);

	}

	private void completedCheck() {
		if (enemies.isEmpty()) {

			lvl++;
			if (lvl == 3) {
				// score = score + lives;
				saveHighScore();

				model.switchState(new HighScoreState(model));

			} else {
				PlayState playstate = new PlayState(model, lvl);
				playstate.setPlayer(player);
				player.setNewPlayState(playstate);
				model.switchState(playstate);
			}

		}

	}

	public void deleteWeapon(Weapon weapon) {
		bullets.remove(weapon);

	}

	@Override
	public void keyPressed(KeyEvent key) {

		System.out.println("Trycker pÃ¥ " + key.getCode() + " i PlayState");

		String s = key.getCode().toString();

		switch (s) {
		case "RIGHT":
			player.setVelX(10);
			break;
		case "LEFT":
			player.setVelX(-10);
			break;
		case "ESCAPE":
			model.switchState(new MenuState(model));
			break;
		case "SPACE":

			player.fireBullet();
		}
	}

	/**
	 * We currently don't have anything to activate in the PlayState so we leave
	 * this method empty in this case.
	 */
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

	public void mousePressed(MouseEvent key) {
		System.out.println("Trycker pÃ¥ musen i playState");

	}

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

	public void gameOver() {

		saveHighScore();
		model.switchState(new GameOverState(model, player.getScore()));

	}

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
		System.out.println(s);

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

		System.out.println("test");
		}

		overwrite(scores, names2);

	}

	// overwrite(scores,names);

	private void overwrite(int[] scores, String[] names) {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Highscores.txt"))) {

			for (int i = 0; i < 10; i++) {

				writer.write(names[i] + " ");
			}

			writer.write("**\n");

			for (int i = 0; i < 10; i++) {

				writer.write(String.valueOf(scores[i]) + " ");
			}

		} catch (IOException e) {

		}

	}

	public Player getPlayer() {

		return this.player;
	}

	public void addNotification(Notification notification) {
	
	this.notification= notification;	
		
	}

	public void deleteNotification(Notification notification) {
	
	notification=null;	
	}

	

}

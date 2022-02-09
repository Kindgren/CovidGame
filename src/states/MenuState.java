package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import constants.Constants;
import controls.MenuButton;

/**
 * This state represents the menu of the Game The main responsibility of this
 * class is to allow the user to swap state to the PlayState
 */
public class MenuState extends GameState {
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the PlayState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */
	private String informationText;
	private Color bgColor;
	private Color fontColor;
	private PlayState playstate;
	// A PlayState, so we can change to the PlayState from the menu.
//	private PlayState playState;
	private MenuButton[] buttons = new MenuButton[3]; 

	public MenuState(GameModel model) {
		super(model);
	//	playState = new PlayState(model,0,0,100);
		informationText = "Press Enter To Play\nEscape to exit";
		bgColor = Color.WHITE;
		fontColor = Color.RED;
		
		
		

		
		MenuButton newGame = new MenuButton(Constants.SCREEN_WIDTH/3+70, Constants.SCREEN_HEIGHT/4, 150, 50, "New Game");
		MenuButton highScores = new MenuButton(Constants.SCREEN_WIDTH/3+70, Constants.SCREEN_HEIGHT/4+100, 150, 50, "Highscores");
		MenuButton exit = new MenuButton(Constants.SCREEN_WIDTH/3+70, Constants.SCREEN_HEIGHT/4+200, 140, 50, "Exit Game");
		buttons[0]=newGame;
		buttons[1]=highScores;
		buttons[2]=exit;
		
	}

	/**
	 * Draws information text to the screen
	 */
	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);

		g.setFill(fontColor);
		g.setFont(new Font(30)); // Big letters
		// Print the information text, centered on the canvas
		
		// Can also use:
		// g.setStroke(fontColor);
		// g.strokeText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		for(int i = 0; i<buttons.length; i++) {
		buttons[i].draw(g);	
		}
		
	}

	/**
	 *
	 * @param key KeyEvent representing the pressed key
	 *
	 *            This function prints the pressed key to the console it's used to
	 *            show that a change of state has been made
	 *
	 *            For more information see GameState
	 */
	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
		//	model.switchState(playState);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		}
	}
	
	
	
	

	/**
	 * We have nothing to update in the menu, no moving objects etc. so we leave the
	 * update method empty.
	 */
	@Override
	public void update() {
	}

	/**
	 * We currently don't have anything to activate in the MenuState so we leave
	 * this method empty in this case.
	 */
	@Override
	public void activate() {

	}

	/**
	 * We currently don't have anything to deactivate in the MenuState so we leave
	 * this method empty in this case.
	 */

	@Override
	public void deactivate() {

	}

	@Override
	public void mousePressed(MouseEvent event) {
	
		int mx = (int)event.getX(); 
		int my = (int)event.getY();
		
		
		for (MenuButton b : buttons) {
			if (mx>=b.getX()&& mx <= b.getX()+b.getWidth()) {
				
				if (my>=b.getY()&& my <= b.getY()+b.getHeight()) {
					
				if(b.getLabel()=="New Game") {
				//model.switchState(playState);
				model.switchState(new PreGameState(model));	
				}
				if(b.getLabel()=="Highscores") {
				model.switchState(new HighScoreState(model));	
					}
				if(b.getLabel()=="Exit Game") {
					System.exit(0);
					}
				
			
		}
				
			
		
		
			}
		}
		}

	@Override
	protected void keyReleased(KeyEvent event) {
		
	}
	
	
}


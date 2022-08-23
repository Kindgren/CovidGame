package states;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import controls.MenuButton;
import controls.PlayerSelector;
import entities.Id;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PreGameState extends GameState {

	private Color bgColor;
	private String informationText1, informationText2;
	private String userName;
	private PlayState playstate;
	private Image bg;
	private PlayerSelector p1, p2;
	private boolean boxClicked;

	public PreGameState(GameModel model, Image bg) {
		super(model);

		bg = bg;
		userName = "";
		bgColor = Color.BLACK;
		informationText1 = "Enter Your Name";
		informationText2 = "Press Enter When Ready";

		p1 = new PlayerSelector(340, 50, 30, 60, "p1", true);
		p2 = new PlayerSelector(390, 50, 30, 60, "p2", false);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GraphicsContext g) {

		drawBgImage(g);

		g.setFill(Color.WHITE);
		g.setFont(new Font(20));
		
		if (boxClicked==false) {
			if (userName=="") {
			g.fillText(informationText1, 110, 80);	
		}
		}
//		g.fillText(informationText2, 100, 180);
		
		

		g.setStroke(Color.WHITE);
		g.strokeRect(100, 50, 180, 50);

		g.setFont(new Font(20));
		g.fillText(userName, 110, 80, 15 * userName.length());

	
	
		p1.draw(g);
		p2.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent key) {

		StringBuffer sb = new StringBuffer(userName);

		if (key.getCode().toString() == "ENTER") {

			playstate = new PlayState(model, 0);

			model.switchState(playstate);

			if (p1.activated()) {
				playstate.initiatePlayer(userName, Id.player1);
				

			} if(p2.activated()) {
				playstate.initiatePlayer(userName, Id.player2);	
				
			}

			return;
		}

		if (key.getCode().toString() == "ESCAPE") {

			model.switchState(new MenuState(model));

			return;
		}

		if (key.getCode().toString() == "BACK_SPACE") {
			if (sb.isEmpty()) {
				return;
			}
			sb.deleteCharAt(sb.length() - 1);
			userName = sb.toString();
			return;
		}

		if (userName.length() >= 10 || key.getCode().toString() == "SHIFT") {
			return;
		}
		if (key.getCode().toString() == "SPACE") {
			userName += " ";

		} else {

			userName += key.getCode().getChar();
		}

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

		if (mx >= p1.getX() && mx <= p1.getX() + p1.getWidth()) {

			if (my >= p1.getY() && my <= p1.getY() + p1.getHeight()) {

				p1.setAsMarked();
				p2.setAsUnmarked();
			}

		}

		if (mx >= p2.getX() && mx <= p2.getX() + p2.getWidth()) {

			if (my >= p2.getY() && my <= p2.getY() + p2.getHeight()) {

				p2.setAsMarked();
				p1.setAsUnmarked();
			}
		}
		
		if (mx >= 100 && mx <= 280) {

			if (my >= 50 && my <= 100) {

				boxClicked = true;
			}
		}else boxClicked=false;

	}

	@Override
	protected void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}

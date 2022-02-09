package states;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import entities.Id;
import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PreGameState extends GameState {

	private Color bgColor;
	private String informationText1, informationText2;
	private String userName;
	private PlayState playstate;

	public PreGameState(GameModel model) {
		super(model);

		userName = "";
		bgColor = Color.WHITE;
		informationText1 = "Please Enter Your Name";
		informationText2 = "Press Enter When Ready";

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GraphicsContext g) {

		drawBg(g, bgColor);

		g.setFill(Color.BLACK);
		g.setFont(new Font(30));
		g.fillText(informationText1, 300, 100);
		g.fillText(informationText2, 300, 250);

		g.setStroke(Color.BLACK);
		g.strokeRect(300, 150, 180, 50);

		g.setFont(new Font(20));
		g.fillText(userName, 330, 180, 15 * userName.length());

	}

	@Override
	public void keyPressed(KeyEvent key) {

		StringBuffer sb = new StringBuffer(userName);

		if (key.getCode().toString() == "ENTER") {

			playstate = new PlayState(model, 0);

			model.switchState(playstate);
			playstate.initiatePlayer(userName);

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
	public void mousePressed(MouseEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub

	}

}

package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import states.PlayState;

public class Notification {

private String note;
private short interval;
private int x,y, i;
private PlayState playstate;
	
public Notification(int x, int y, PlayState playstate, String s) {
this.note=s;
this.x=x;
this.y=y;

this.playstate=playstate;

}

public void Draw(GraphicsContext g) {
	

	if (this.interval > 1) {
		this.interval = 0;
		i++;
	}

	if (i > 4) {
		playstate.deleteNotification(this);
		return;
	} else {
		g.setFill(Color.BLACK);
		g.setFont(new Font("Corier", 20));
		g.fillText(note, x + 5 + (i * 2), y - (i * 3));
	}

	
interval++;	
	
}

}

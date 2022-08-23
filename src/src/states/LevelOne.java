package states;

import entities.Covid;
import entities.Enemy;
import entities.Id;
import javafx.scene.paint.Color;


public class LevelOne extends Levels {

	
/*	Class responsible for creating the objects in the first level. Passes the created enemies to PlayState.
	Also has a unique background which Playstate uses in its Draw() method
*/

	
	public LevelOne(PlayState playstate) {
		super(playstate);
		
		
		
		bgColor = Color.LIGHTCYAN;
	
		
		Enemy enemy1 = new Covid(10, 10, 40, 40, Id.covid, playstate,1);
		Enemy enemy2 = new Covid(90, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy3 = new Covid(170, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy4 = new Covid(250, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy5 = new Covid(330, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy6 = new Covid(410, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy7 = new Covid(490, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy8 = new Covid(570, 10, 40, 40, Id.covid, playstate,0);

		playstate.addEnemy(enemy1);
		playstate.addEnemy(enemy2);
		playstate.addEnemy(enemy3);
		playstate.addEnemy(enemy4);
		playstate.addEnemy(enemy5);
		playstate.addEnemy(enemy6);
		playstate.addEnemy(enemy7);
		playstate.addEnemy(enemy8);
		
		
		
		
	

	}
	
	public Color getBgColor() {
	return this.bgColor;	
	}
	


}

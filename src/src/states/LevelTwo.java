package states;

import entities.Delta;
import entities.Enemy;
import entities.Id;
import javafx.scene.paint.Color;


/*	Class responsible for creating the objects in the second level. Passes the created enemies to PlayState.
Also has a unique background which Playstate uses in its Draw() method
*/


public class LevelTwo extends Levels{


	private Color bgColor;

	

	
	public LevelTwo(PlayState playstate) {
		super(playstate);
		
		bgColor = Color.BEIGE;

		
		Enemy enemy1 = new Delta(10, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy2 = new Delta(60, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy3 = new Delta(110, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy4 = new Delta(160, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy5 = new Delta(200, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy6 = new Delta(300, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy7 = new Delta(400, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy8 = new Delta(560, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy9 = new Delta(660, 10, 40, 40, Id.delta, playstate,0,-1);
		
		playstate.addEnemy(enemy1);
		playstate.addEnemy(enemy2);
		playstate.addEnemy(enemy3);
		playstate.addEnemy(enemy4);
		playstate.addEnemy(enemy5);
		playstate.addEnemy(enemy6);
		playstate.addEnemy(enemy7);
		playstate.addEnemy(enemy8);
		playstate.addEnemy(enemy9);
		
		
		
	
		
		
		
		
	}
	
	public Color getBgColor() {
		return this.bgColor;	
		}
		
		
	
	
	
}

package states;

import entities.Covid;
import entities.Enemy;
import entities.Id;
import javafx.scene.paint.Color;


public class LevelOne extends Levels {

	
	


	
	public LevelOne(PlayState playstate) {
		super(playstate);
		
		
		
		bgColor = Color.LIGHTCYAN;
	
		
		Enemy enemy1 = new Covid(10, 10, 40, 40, Id.enemy, playstate,0);
		Enemy enemy2 = new Covid(90, 10, 40, 40, Id.enemy, playstate,0);
		Enemy enemy3 = new Covid(180, 10, 40, 40, Id.enemy, playstate,0);
		Enemy enemy4 = new Covid(350, 10, 40, 40, Id.enemy, playstate,0);
		Enemy enemy5 = new Covid(400, 10, 40, 40, Id.enemy, playstate,0);
		Enemy enemy6 = new Covid(600, 10, 40, 40, Id.enemy, playstate,1);

		playstate.addEnemy(enemy1);
		playstate.addEnemy(enemy2);
		playstate.addEnemy(enemy3);
		playstate.addEnemy(enemy4);
		playstate.addEnemy(enemy5);
		playstate.addEnemy(enemy6);
		
		
		
		
	

	}
	
	public Color getBgColor() {
	return this.bgColor;	
	}
	


}

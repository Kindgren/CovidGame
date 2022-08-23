package states;

import entities.Covid;
import entities.Delta;
import entities.Enemy;
import entities.Id;
import javafx.scene.paint.Color;

public class LevelThree extends Levels {
	
	

	public LevelThree(PlayState playstate) {
		super(playstate);
		
	bgColor = Color.LIGHTGRAY;
		

		Enemy enemy1 = new Delta(10, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy2 = new Delta(60, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy3 = new Delta(110, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy4 = new Delta(150, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy5 = new Delta(250, 10, 40, 40, Id.delta, playstate,0,-1);
		Enemy enemy6 = new Delta(350, 10, 40, 40, Id.delta, playstate,0,1);
		Enemy enemy7 = new Covid(160, 10, 40, 40, Id.covid, playstate,2);
		Enemy enemy8 = new Covid(200, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy9 = new Covid(300, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy10 = new Covid(400, 10, 40, 40, Id.covid, playstate,0);
		Enemy enemy11 = new Covid(500, 10, 40, 40, Id.covid, playstate,0);
		
		playstate.addEnemy(enemy1);
		playstate.addEnemy(enemy2);
		playstate.addEnemy(enemy3);
		playstate.addEnemy(enemy4);
		playstate.addEnemy(enemy5);
		playstate.addEnemy(enemy6);
		playstate.addEnemy(enemy7);
		playstate.addEnemy(enemy8);
		playstate.addEnemy(enemy9);
		playstate.addEnemy(enemy10);
		playstate.addEnemy(enemy11);
		
		
		
	
		
		
		
		
	}
	
	public Color getBgColor() {
		return this.bgColor;	
		}
		
		

	
	
	
	
	}



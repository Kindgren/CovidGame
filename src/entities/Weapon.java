package entities;

import java.awt.Rectangle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import states.PlayState;

public class Weapon  {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private int velX,velY;
	private int damage;
	private Id id;
	private Image image;
	

	public Weapon(int x, int y, int width, int height,int velY) {
		this.x=x;
	    this.y=y;
	    this.velY=velY;
	    this.width=width;
	    this.height=height;
	  
	    
		
	}
	
	
	public void update() {
		x+=this.velX;	
		y+=this.velY;
	
		
	}
	
	public  void draw(GraphicsContext g) {
		
		
	g.drawImage(image, x, y, width, height);	
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);

		}


	public Id getId() {
		
		return this.id;
	}


	public void explode(PlayState playstate, int velX, int velY) {
       
		
		Explosion e = new Explosion(this.x,this.y,15, 15, velX, velY,playstate);
        playstate.addExplosion(e);
		
	}


	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public void setId(Id id) {
	
	this.id = id;
		
	}


	public void setImage(Image image) {
	
	
		this.image = image;
		
	}


	


	

}

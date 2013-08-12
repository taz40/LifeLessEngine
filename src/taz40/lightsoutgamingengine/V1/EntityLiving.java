package taz40.lightsoutgamingengine.V1;

import java.awt.Graphics2D;

public abstract class EntityLiving extends Entity {

	public EntityLiving(Screen screen, float x, float y, int width, int height, double rotation) {
		super(screen);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.rotation = rotation;
		this.width = width;
		this.height = height;
	}
	
	public float x;
	public float y;
	public double rotation;
	public int width;
	public int height;
	
	public abstract void OnCollide(EntityLiving e);
	
	public void kill(){
		screen.removeEntity(this);
	}
	
}

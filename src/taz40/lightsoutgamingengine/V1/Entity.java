package taz40.lightsoutgamingengine.V1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Entity {
	
	public Entity(Screen screen){
		this.screen = screen;
	}
	
	public Screen screen;
	public boolean visible = true;

public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public abstract void onCustomCreate();
	
	public abstract void onCustomUpdate();
	
	public abstract void onCustomDraw(Graphics2D g2d);
	
	public void onCreate(){
		this.onCustomCreate();
	}
	
	public void onUpdate(){
		for(int c = 0; c < entities.size(); c++){
			if(entities.get(c) instanceof EntityLiving){
				EntityLiving e = (EntityLiving)entities.get(c);
				for(int i = 0; i < entities.size(); i++){
					if(e != entities.get(i)){
						if(entities.get(i) instanceof EntityLiving){
							EntityLiving ent = (EntityLiving) entities.get(i);
							if(e.x+e.width >= ent.x && e.x <= ent.x+ent.width){
								if(e.y+e.height >= ent.y && e.y <= ent.y+ent.height){
									System.out.println("collision");
									e.OnCollide(ent);
								}
							}
						}
					}
				}
			}
		}
		this.onCustomUpdate();
		for(int i=0; i<entities.size(); i++){
			entities.get(i).onUpdate();
		}
	}
	
	public void onDraw(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		if(visible){
			this.onCustomDraw(g2d);
		}
		g2d.dispose();
		for(int i=0; i<entities.size(); i++){
			g2d = (Graphics2D)g.create();
			entities.get(i).onDraw(g2d);
			g2d.dispose();
		}
	}
	
	public Screen getScreen(){
		return screen;
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
		entity.onCreate();
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void removeEntity(int i){
		entities.remove(i);
	}
	
	public Entity getEntity(int i){
		return entities.get(i);
	}
	
}

package taz40.lightsoutgamingengine.V1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MousePadListener implements MouseListener, MouseMotionListener {

	private int MouseX, MouseY;
	private boolean clicked;
	private Game game;
	
	public MousePadListener(Game g){
		game = g;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		MouseX = event.getX();
		MouseY = event.getY();
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseClicked(event);
		clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseClicked(event);
		clicked = false;
	}
	
	public boolean isMousePressed(){
		return clicked;
	}
	
	public boolean isMouseReleaced(){
		return !clicked;
	}
	
	public int getX(){
		return MouseX;
	}
	
	public int getY(){
		return MouseY;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MouseX = e.getX();
		MouseY = e.getY();
	}
	
	public void clickDone(){
		clicked = false;
	}
	
}

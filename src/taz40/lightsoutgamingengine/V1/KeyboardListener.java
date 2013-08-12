package taz40.lightsoutgamingengine.V1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private boolean[] keys = new boolean[100000];
	
	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		keys[event.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		keys[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isKeyPressed(int key){
		return keys[key];
	}
	
	public boolean isKeyReleased(int key){
		return !keys[key];
	}
	
	public void unpresskey(int key){
		keys[key] = false;
	}

}

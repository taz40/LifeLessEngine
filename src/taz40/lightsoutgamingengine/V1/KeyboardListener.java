package taz40.lightsoutgamingengine.V1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardListener implements KeyListener {

	private boolean[] keys = new boolean[256];
	
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
	
	public Integer[] getPressedKeys(){
		ArrayList<Integer> pkeys = new ArrayList<Integer>();
		Integer[] pkeysa = new Integer[256];
		for(int i = 0; i < 256;i++){
			if(keys[i]){
				pkeys.add(i);
			}
		}
		pkeys.toArray(pkeysa);
		return pkeysa;
	}

}

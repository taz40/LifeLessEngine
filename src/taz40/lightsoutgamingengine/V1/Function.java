package taz40.lightsoutgamingengine.V1;

import taz40.lightsoutgamingengine.V1.Entity;

public abstract class Function implements Runnable {
	
	public Screen s;
	
	public Function(Screen screen){
		s = screen;
	}
	
}

package taz40.lightsoutgamingengine.V1;

public abstract class Function implements Runnable {
	
	public Screen s;
	
	public Function(Screen screen){
		s = screen;
	}
	
}

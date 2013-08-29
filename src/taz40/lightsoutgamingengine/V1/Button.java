package taz40.lightsoutgamingengine.V1;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import taz40.lightsoutgamingengine.V1.Entity;
import taz40.lightsoutgamingengine.V1.Screen;
import taz40.lightsoutgamingengine.V1.TextureRenderer;

public class Button extends Entity {
	
	private String title;
	private int X,Y,WIDTH,HEIGHT;
	private boolean hover = false;
	private int txtScale = 1;
	private Function func;
	private int buttonpressed;
	private int buttonunpressed;
	
	public static Button createXCenteredButton(String title, int y, int width, int height, int scale, int ButtonPressed, int ButtonUnpressed, Function func, Screen screen){
		return new Button(screen, title, (screen.getScreenFactory().getGame().getWindow().getWidth()/2)-(width/2), y, width, height, scale, ButtonPressed, ButtonUnpressed, func);
	}
	
	public Button(Screen screen, String title, int x, int y, int width, int height, int txtscale, int ButtonPressed, int ButtonUnpressed, Function function) {
		super(screen);
		this.title = title;
		X = x;
		Y = y;
		WIDTH = width;
		HEIGHT = height;
		txtScale = txtscale;
		func = function;
		buttonpressed = ButtonPressed;
		buttonunpressed = ButtonUnpressed;
		// TODO Auto-generated constructor stub
	}
	
	public void setTitle(String ntitle){
		title = ntitle;
	}
	

	@Override
	public void onCustomCreate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCustomDraw(Graphics2D g) {
		// TODO Auto-generated method stub
		int texture = 0;
		if(hover){
			texture = buttonpressed;
		}else{
			texture = buttonunpressed;
		}
		g.scale(1, 1);
		TextureRenderer.DrawTexture(texture, X, Y, WIDTH, HEIGHT, g);
		int strX, strY;
		strY = (Y+(HEIGHT/2)+(4*txtScale))/txtScale;
		@SuppressWarnings("deprecation")
		FontMetrics fm =  Toolkit.getDefaultToolkit().getFontMetrics(g.getFont());
		int length = fm.stringWidth(title)*txtScale;
		strX = (((X+(WIDTH/2))-(length/2))/txtScale);
		g.setColor(Color.black);
		g.scale(txtScale, txtScale);
		g.drawString(title, strX, strY);
	}

	@Override
	public void onCustomUpdate() {
		// TODO Auto-generated method stub
		if(screen.getScreenFactory().getGame().getMousePadListener().isMousePressed()){
			Point mouse = new Point();
			int y = screen.getScreenFactory().getGame().getMousePadListener().getY();
			if(!screen.getScreenFactory().getGame().fullscreen){
				y -= 25;
			}
			if(screen.getScreenFactory().getGame().getMousePadListener().getX() > X && screen.getScreenFactory().getGame().getMousePadListener().getX() < (X + WIDTH)-1){
				if(y > Y && y < (Y + HEIGHT)){
					screen.getScreenFactory().getGame().getMousePadListener().clickDone();
					new Thread(func, title+" Thread").start();
				}
			}
		}
		int y = screen.getScreenFactory().getGame().getMousePadListener().getY();
		if(!screen.getScreenFactory().getGame().fullscreen){
			y -= 25;
		}
		if(screen.getScreenFactory().getGame().getMousePadListener().getX() > X && screen.getScreenFactory().getGame().getMousePadListener().getX() < (X + WIDTH)-1){
			System.out.println(title+" in X");
			if(y > Y && y < (Y + HEIGHT)){
				this.hover = true;
			}else{
				this.hover = false;
			}
		}else{
			this.hover = false;
		}
		
	}

	@Override
	public void onCustomDestroy() {
		// TODO Auto-generated method stub
		
	}

}

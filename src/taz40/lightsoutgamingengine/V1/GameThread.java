package taz40.lightsoutgamingengine.V1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GameThread extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Game game;
	private static boolean running = true;
	public static boolean done = false;
	public long msin1frame;
	public boolean skipdrawnextframe = false;
	
	public GameThread(Game game){
		this.game = game; 
		this.setFocusable(true);
	}
	
	public void exit(){
		running=false;
		done = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			msin1frame = 1000/game.fps;
			try{
				if(game.getScreenFactory().getCurrentScreen()!= null){
					long curms = System.currentTimeMillis();
					game.getScreenFactory().getCurrentScreen().onUpdate();
					if(!skipdrawnextframe){
						repaint();
					}else{
						skipdrawnextframe = false;
					}
					long timeelapsed = System.currentTimeMillis() - curms;
					if(timeelapsed > msin1frame){
						skipdrawnextframe = true;
					}else if(timeelapsed < msin1frame){
						Thread.sleep((long) (msin1frame-timeelapsed));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(game.getScreenFactory().getCurrentScreen() != null){
			game.getScreenFactory().getCurrentScreen().onDraw(g2d);
		}
	}
	
	
	
}

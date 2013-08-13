package taz40.lightsoutgamingengine.V1;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

public class Game {

	private JFrame window = new JFrame();
	private final ScreenFactory screenfactory;
	public final GameThread gamethread;
	private final KeyboardListener keyboardlistener;
	private final MousePadListener mousepadlistener;
	private Thread t;
	private final int windowX;
	private final int windowY;
	private final String windowTitle;
	private GraphicsDevice vc;
	public final TextureRenderer texturerenderer = new TextureRenderer();
	public boolean fullscreen = false;
	public int fps;
	
	public void setFullScreen(boolean fullscreen){
		if(fullscreen){
			/*window.setResizable(true);
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
			window.setResizable(false);
			window.setUndecorated(true);
			window.update(window.getGraphics());*/
			DisplayMode dm = new DisplayMode(windowX, windowY, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
			window.dispose();
			window = new JFrame(windowTitle);
			window.setFocusable(true);
			window.add(gamethread);
			window.addKeyListener(keyboardlistener);
			window.addMouseListener(mousepadlistener);
			window.addMouseMotionListener(mousepadlistener);
			window.setUndecorated(true);
			vc.setFullScreenWindow(window);
			if(dm != null && vc.isDisplayChangeSupported()){
				try{
					vc.setDisplayMode(dm);
				}catch(Exception e){}
			}
			
		}else{
			/*window.setVisible(false);
			window.setResizable(true);
			window.setExtendedState(JFrame.NORMAL);
			window.setUndecorated(false);
			window.setSize(windowX, windowY);
			window.setResizable(false);
			window.setLocationRelativeTo(null);
			window.setVisible(true);
			window.update(window.getGraphics());*/
			
			window.dispose();
			window = new JFrame(windowTitle);
			window.setSize(windowX, windowY);
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setFocusable(true);
			window.setLocationRelativeTo(null);
			window.setVisible(true);
			window.add(gamethread);
			window.addKeyListener(keyboardlistener);
			window.addMouseListener(mousepadlistener);
			window.addMouseMotionListener(mousepadlistener);
			vc.setFullScreenWindow(null);
			
		}
		this.fullscreen = fullscreen;
	}
	
	public Game(int windowX, int windowY, String title, int fps){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
		this.fps = fps;
		this.windowTitle = title;
		this.windowX = windowX;
		this.windowY = windowY;
		window.setSize(windowX, windowY);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
		screenfactory = new ScreenFactory(this);
		
		gamethread = new GameThread(this);
		window.add(gamethread);
		keyboardlistener = new KeyboardListener();
		window.addKeyListener(keyboardlistener);
		mousepadlistener = new MousePadListener(this);
		window.addMouseListener(mousepadlistener);
		window.addMouseMotionListener(mousepadlistener);
		
		t = new Thread(gamethread, "Game Tread");
		t.start();
	}
	
	public KeyboardListener getKeyboardListener(){
		return keyboardlistener;
	}
	
	public MousePadListener getMousePadListener(){
		return mousepadlistener;
	}
	
	public ScreenFactory getScreenFactory(){
		return screenfactory;
	}
	
	public JFrame getWindow(){
		return window;
	}
	
	public void Exit(){
		System.out.println("Exit initiated");
		screenfactory.onDestroy();
		gamethread.exit();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting");
		System.out.println("Almost There!");
		window.dispose();
		vc.setFullScreenWindow(null);
		System.out.println("Finished");
	}
	
}

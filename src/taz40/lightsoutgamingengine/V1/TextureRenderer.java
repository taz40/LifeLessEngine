package taz40.lightsoutgamingengine.V1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class TextureRenderer {

	static ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	static ArrayList<BufferedImage> spriteSheets = new ArrayList<BufferedImage>();
	static ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	
	public static void DrawTexture(int texture, int x, int y, int width, int height, Graphics2D g){
		g.drawImage(images.get(texture), x, y, x+width, y+height, 0, 0, width, height, null);
		
	}
	
	public static void DrawSprite(int texture, int x, int y, int scale, double rotation, Graphics2D g){
		BufferedImage image = sprites.get(texture);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.scale(scale, scale);
		double rotationRequired = Math.toRadians(rotation);
		double locationX = image.getWidth() / 2;
		double locationY = image.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		//Drawing the rotated image at the required drawing locations
		g2d.drawImage(op.filter(image, null), x, y, x+(image.getWidth()*scale), y+(image.getHeight()*scale), 0, 0, image.getWidth(), image.getHeight(), null);
		g2d.dispose();
	}
	
	public void DrawSprite(int texture, int x, int y, int scale, Graphics2D g){
		DrawSprite(texture, x, y, scale, 0, g);
	}
	
	private void DrawSpriteTexture(int texture, int x, int y, int width, int height, Graphics2D g){
		g.drawImage(spriteSheets.get(texture), 0, 0, width, height, x, y, width+x, height+y, null);
	}
	
	protected ImageIcon createImageIcon(String path,
            String description, Class c) {
java.net.URL imgURL = c.getResource(path);
if (imgURL != null) {
return new ImageIcon(imgURL, description);
} else {
System.err.println("Couldn't find file: " + path);
return null;
}
}
	
	public int LoadTexture(String path, Class c){
		ImageIcon i = createImageIcon(path, "textures", c);
		BufferedImage bi = new BufferedImage(i.getIconWidth(), i.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(i.getImage(), 0, 0, bi.getWidth(), bi.getHeight(), 0, 0, bi.getWidth(), bi.getHeight(), null);
		images.add(bi);
		return images.indexOf(bi);
	}
	
	public int LoadTexture(String path){
		ImageIcon i = new ImageIcon(path, "texture");;
		BufferedImage bi = new BufferedImage(i.getIconWidth(), i.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(i.getImage(), 0, 0, bi.getWidth(), bi.getHeight(), 0, 0, bi.getWidth(), bi.getHeight(), null);
		images.add(bi);
		return images.indexOf(bi);
	}
	
	public  int LoadSpritesheet(String path, Class c){
		ImageIcon i = createImageIcon(path, "textures", c);
		BufferedImage bi = new BufferedImage(i.getIconWidth(), i.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		bi.getGraphics().drawImage(i.getImage(), 0, 0, bi.getWidth(), bi.getHeight(), 0, 0, bi.getWidth(), bi.getHeight(), null);
		spriteSheets.add(bi);
		return spriteSheets.indexOf(bi);
	}
	
	public int LoadSprite(int spritesheet, int x, int y, int width, int height){
		BufferedImage sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = sprite.getGraphics();
		DrawSpriteTexture(spritesheet, x, y, width, height, (Graphics2D) g);
		g.dispose();
		sprites.add(sprite);
		return sprites.indexOf(sprite);
	}
	
}

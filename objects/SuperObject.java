package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int screenX;
	public int screenY;
	public Rectangle solidArea = new Rectangle(0, 0, 24, 24);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public int solidAreaDefaultWidth = 24;
	public int solidAreaDefaultHeight = 24;
	
	public void draw(GamePanel gp, Graphics2D g2) {
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}

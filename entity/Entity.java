package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int screenX;
	public int screenY;
	public int speed;
	public String direction;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage bodyUp, bodyDown, bodyLeft, bodyRight;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public boolean collisionOn = false;
	public Rectangle solidArea;
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public int solidAreaDefaultWidth;
	public int solidAreaDefaultHeight;
}

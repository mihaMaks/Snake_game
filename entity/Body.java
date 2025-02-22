package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Body {
	BufferedImage imageUp, imageDown, imageLeft, imageRight;
	BufferedImage turn1, turn2, turn3, turn4;
	String name;
	public String direction;
	int screenX, screenY, height, width;
	public Rectangle solidArea;

	public Body(String name, String direction,int screenX, int screenY, int height, int width) {
		this.name = name;
		this.direction = direction;
		this.height = 24;
		this.width = 24;
		this.screenX = screenX;
		this.screenY = screenY;
		this.solidArea = new Rectangle(screenX, screenY, width, height);
		
		getImages(); 
	}
	public void getImages() {
		if(name == "head") {
			try {
				imageUp = ImageIO.read(getClass().getResourceAsStream("/snake/headUp.png"));
				imageDown = ImageIO.read(getClass().getResourceAsStream("/snake/headDown.png"));
				imageLeft = ImageIO.read(getClass().getResourceAsStream("/snake/headLeft.png"));
				imageRight = ImageIO.read(getClass().getResourceAsStream("/snake/headRight.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
		}if(name == "body") {
			try {
				imageUp = ImageIO.read(getClass().getResourceAsStream("/snake/bodyUp.png"));
				imageDown = ImageIO.read(getClass().getResourceAsStream("/snake/bodyDown.png"));
				imageLeft = ImageIO.read(getClass().getResourceAsStream("/snake/bodyLeft.png"));
				imageRight = ImageIO.read(getClass().getResourceAsStream("/snake/bodyRight.png"));
				
				turn1 = ImageIO.read(getClass().getResourceAsStream("/snake/leftUp.png"));
				turn2 = ImageIO.read(getClass().getResourceAsStream("/snake/upLeft.png"));
				turn3 = ImageIO.read(getClass().getResourceAsStream("/snake/rightUp.png"));
				turn4 = ImageIO.read(getClass().getResourceAsStream("/snake/upRight.png"));
				
				
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}if(name == "tail") {
			try {
				imageUp = ImageIO.read(getClass().getResourceAsStream("/snake/tailUp.png"));
				imageDown = ImageIO.read(getClass().getResourceAsStream("/snake/tailDown.png"));
				imageLeft = ImageIO.read(getClass().getResourceAsStream("/snake/tailLeft.png"));
				imageRight = ImageIO.read(getClass().getResourceAsStream("/snake/tailRight.png"));
				
			}catch(IOException e) {
				e.printStackTrace();
			}

		}
		
	}
}

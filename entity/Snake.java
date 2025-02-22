package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Snake extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	int miceEaten;
	int miceCounter;
	public int length;
	public Body[] snake;
	
	
	
	public Snake(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = (gp.maxScreenCol/2) * gp.tileSize;
		screenY = (gp.maxScreenRow/2) * gp.tileSize;
		speed = 24;
		direction = "up";
		
		
		solidArea = new Rectangle(0, 0, 23, 23);
		solidAreaDefaultX = 0;
		solidAreaDefaultY = 0;
		solidAreaDefaultWidth = 23;
		solidAreaDefaultHeight = 23;
		miceCounter = 1;
		miceEaten = 1;
		length = 0;
		snake = new Body[100];
		
		
		//add head
		snake[0] = new Body("head", "up", screenX, screenY, 23, 23);
		
		//snake[1] = new Body("body", "up", 24, 24);
		snake[1] = new Body("tail", "up",screenX, screenY+gp.tileSize, 23, 23);
		
		//getImage();
	}
	/*
	public void getImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/snake/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/snake/up2.png"));
			bodyUp = ImageIO.read(getClass().getResourceAsStream("/snake/up2.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/snake/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/snake/down2.png"));
			bodyDown = ImageIO.read(getClass().getResourceAsStream("/snake/down2.png"));

			right1 = ImageIO.read(getClass().getResourceAsStream("/snake/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/snake/right2.png"));
			bodyRight = ImageIO.read(getClass().getResourceAsStream("/snake/right2.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/snake/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/snake/left2.png"));
			bodyLeft = ImageIO.read(getClass().getResourceAsStream("/snake/left2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				//snake[0].direction = direction;
				
			}if(keyH.downPressed == true) {
				direction = "down";
				//snake[0].direction = direction;
				
			}if(keyH.leftPressed == true) {
				direction = "left";
				//snake[0].direction = direction;
				
			}if(keyH.rightPressed == true) {
				direction = "right";
				//snake[0].direction = direction;
				
			}
		}
		
		
		
		
		collisionOn = false;
		gp.cMan.checkTile(this);
		gp.cMan.checkSnake(this);
		if(collisionOn == false) {
			for(int i = length+1; i > 0; i--) {
				snake[i].direction = snake[i-1].direction;
			}
			snake[0].direction = direction;
		}
		
		
		int objIndex = gp.cMan.checkObject(this, true);
		pickUpItem(objIndex);
		
		if(collisionOn == false) {
			switch(direction) {
				case "up": screenY -= speed; break;
				case "down": screenY += speed; break;
				case "left": screenX -= speed; break;
				case "right": screenX += speed; break;
			}
		}
		
		
		
		spriteCounter++;
		if(spriteCounter > 5) {
			spriteCounter = 0;
			if(spriteNum == 1) {
				spriteNum = 0;
			}else {
				spriteNum = 1;
			}
		}
	}
	
	public void pickUpItem(int i) {
		if(i != 999) {
			miceEaten++;
			miceCounter++;
			gp.obj[i] = null;
			System.out.println("mice eaten:"+miceEaten);
		}
		if(miceCounter % 2 == 0) {
			miceCounter = 1;
			length++;
			Body t = snake[length];
			snake[length] = new Body("body", t.direction,0, 0, 23, 23);
			snake[length+1] = t;
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		Body bdy1 = null;
		Body bdy2 = null;
		int imageX = screenX;
		int imageY = screenY;
		
		for(int i = 0; i < length+1; i++) {
			bdy1 = snake[i];
			
			switch(bdy1.direction) {
			case "up": 
				image = bdy1.imageUp;
				//imageY += gp.tileSize;
				break;
			case "down": 
				image = bdy1.imageDown;
				//imageY -= gp.tileSize;
				break;
			case "left":
				image = bdy1.imageLeft;
				//imageX -= gp.tileSize;
				break;
			case "right":
				image = bdy1.imageRight;
				//imageX += gp.tileSize;
				break;
				
			}
			if(i == 0) {
				
			}else {
				bdy2 = snake[i-1];
				if(bdy2.direction == "up") {
					imageY += gp.tileSize;
					if(bdy1.direction == "left") {
						image = bdy1.turn1;
					}if(bdy1.direction == "right") {
						image = bdy1.turn3;
					}
				}if(bdy2.direction == "down") {
					imageY -= gp.tileSize;
					if(bdy1.direction == "left") {
						image = bdy1.turn4;
					}if(bdy1.direction == "right") {
						image = bdy1.turn2;
					}
				}if(bdy2.direction == "left") {
					imageX += gp.tileSize;
					if(bdy1.direction == "up") {
						image = bdy1.turn2;
					}if(bdy1.direction == "down") {
						image = bdy1.turn3;
					}
				}if(bdy2.direction == "right") {
					imageX -= gp.tileSize;
					if(bdy1.direction == "up") {
						image = bdy1.turn4;
					}if(bdy1.direction == "down") {
						image = bdy1.turn1;
					}
				}
				
				
			}
			
			g2.drawImage(image, imageX, imageY, bdy1.width, bdy1.height, null);
		
		}
		//draw tail
		bdy1 = snake[length+1];
		bdy2 = snake[length];
		switch(bdy2.direction) {
		case "up": 
			image = bdy1.imageUp;
			imageY += gp.tileSize;
			break;
		case "down": 
			image = bdy1.imageDown;
			imageY -= gp.tileSize;
			break;
		case "left":
			image = bdy1.imageLeft;
			imageX += gp.tileSize;
			break;
		case "right":
			image = bdy1.imageRight;
			imageX -= gp.tileSize;
			break;
		
		}
		g2.drawImage(image, imageX, imageY, bdy1.width, bdy1.height, null);
		
			
			
	}
	
}
